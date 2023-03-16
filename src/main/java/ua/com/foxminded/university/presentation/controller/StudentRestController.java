package ua.com.foxminded.university.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.foxminded.university.consumer.dto.StudentDTO;
import ua.com.foxminded.university.service.StudentService;
import ua.com.foxminded.university.service.exception.StudentAlreadyExistException;
import ua.com.foxminded.university.service.exception.StudentNotFoundException;

@RestController
@RequestMapping("/api/v1/students")
public class StudentRestController {

    private final StudentService service;

    @Autowired
    public StudentRestController(StudentService service) {
        this.service = service;
    }

    @GetMapping()
    public Page<StudentDTO> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> findById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
        } catch (StudentNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StudentDTO(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<StudentDTO> create(@RequestBody StudentDTO student) {
        try {
            return new ResponseEntity<>(service.create(student), HttpStatus.CREATED);
        } catch (StudentAlreadyExistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(student, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> update(@RequestBody StudentDTO student) {
        try {
            return new ResponseEntity<>(service.update(student), HttpStatus.OK);
        } catch (StudentNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(student, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(service.delete(id), HttpStatus.NO_CONTENT);
        } catch (StudentNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
}

