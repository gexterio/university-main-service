package ua.com.foxminded.university.presentation.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
