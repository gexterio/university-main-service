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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ua.com.foxminded.university.consumer.dto.TeacherDTO;
import ua.com.foxminded.university.service.TeacherService;
import ua.com.foxminded.university.service.exception.TeacherAlreadyExistException;
import ua.com.foxminded.university.service.exception.TeacherNotFoundException;

@RestController
@RequestMapping("/api/v1/teachers")
public class TeacherRestController {

    private final TeacherService service;

    @Autowired
    public TeacherRestController(TeacherService service) {
        this.service = service;
    }

    @GetMapping()
    public Page<TeacherDTO> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> findById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
        } catch (TeacherNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new TeacherDTO(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<TeacherDTO> create(@RequestBody TeacherDTO teacher) {
        try {
            return new ResponseEntity<>(service.create(teacher), HttpStatus.CREATED);
        } catch (TeacherAlreadyExistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(teacher, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDTO> update(@RequestBody TeacherDTO teacher) {
        try {
            return new ResponseEntity<>(service.update(teacher), HttpStatus.OK);
        } catch (TeacherNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(teacher, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(service.delete(id), HttpStatus.NO_CONTENT);
        } catch (TeacherNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
}
