package ua.com.foxminded.university.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.foxminded.university.consumer.dto.StudentDTO;
import ua.com.foxminded.university.service.StudentService;

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

}
