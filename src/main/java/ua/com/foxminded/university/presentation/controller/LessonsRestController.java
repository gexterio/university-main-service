package ua.com.foxminded.university.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ua.com.foxminded.university.consumer.dto.LessonDTO;
import ua.com.foxminded.university.service.LessonService;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class LessonsRestController {

    private final LessonService service;

    @Autowired
    public LessonsRestController(LessonService service) {
        this.service = service;
    }

    @GetMapping("/students/{id}/lessons")
    public List<LessonDTO> findLessonsForStudent(@PathVariable("id") Long id,
                                                 @RequestParam("range") String range,
                                                 @RequestParam("isoDate")
                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String isoDate) {
        if (range.equals("day")) {
            ZonedDateTime day = ZonedDateTime.parse(isoDate, DateTimeFormatter.ISO_ZONED_DATE_TIME);
            return service.findLessonsForStudentForDay(id, day);
        } else if (range.equals("month")) {
            ZonedDateTime month = ZonedDateTime.parse(isoDate, DateTimeFormatter.ISO_ZONED_DATE_TIME);
            return service.findLessonsForStudentForMonth(id, month);
        } else throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }


    @GetMapping("/teachers/{id}/lessons")
    public List<LessonDTO> findLessonsForTeacher(@PathVariable("id") Long id,
                                                 @RequestParam("range") String range,
                                                 @RequestParam("isoDate")
                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String isoDate) {
        if (range.equals("day")) {
            ZonedDateTime day = ZonedDateTime.parse(isoDate, DateTimeFormatter.ISO_ZONED_DATE_TIME);
            return service.findLessonsForTeacherForDay(id, day);
        } else if (range.equals("month")) {
            ZonedDateTime month = ZonedDateTime.parse(isoDate, DateTimeFormatter.ISO_ZONED_DATE_TIME);
            return service.findLessonsForTeacherForMonth(id, month);
        } else throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
}
