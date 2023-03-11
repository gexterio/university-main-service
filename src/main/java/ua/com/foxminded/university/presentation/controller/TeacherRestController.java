package ua.com.foxminded.university.presentation.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ua.com.foxminded.university.consumer.dto.TeacherDTO;
import ua.com.foxminded.university.service.TeacherService;

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
    public TeacherDTO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeacherDTO create(@RequestBody TeacherDTO teacher) {
        return service.create(teacher);
    }

    @PutMapping("/{id}")
    public TeacherDTO update(@RequestBody TeacherDTO teacher) {
        return service.update(teacher);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        if (!service.delete(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
