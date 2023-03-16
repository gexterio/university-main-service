package ua.com.foxminded.university.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.foxminded.university.consumer.dto.LessonDTO;
import ua.com.foxminded.university.service.LessonService;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
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
    public List<LessonDTO> findLessonsForStudent(@PathVariable("id") Long studentId,
                                                 @RequestParam("range") String range,
                                                 @RequestParam("isoDate")
                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String isoDate) {
        if ("day".equals(range)) {
            ZonedDateTime day = ZonedDateTime.parse(isoDate, DateTimeFormatter.ISO_ZONED_DATE_TIME);
            return service.findLessonsForStudentForDay(studentId, day);
        } else if ("month".equals(range)) {
            ZonedDateTime month = ZonedDateTime.parse(isoDate, DateTimeFormatter.ISO_ZONED_DATE_TIME);
            return service.findLessonsForStudentForMonth(studentId, month);
        } else return Collections.emptyList();
    }


    @GetMapping("/teachers/{id}/lessons")
    public List<LessonDTO> findLessonsForTeacher(@PathVariable("id") Long teacherId,
                                                 @RequestParam("range") String range,
                                                 @RequestParam("isoDate")
                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String isoDate) {
        if ("day".equals(range)) {
            ZonedDateTime day = ZonedDateTime.parse(isoDate, DateTimeFormatter.ISO_ZONED_DATE_TIME);
            return service.findLessonsForTeacherForDay(teacherId, day);
        } else if ("month".equals(range)) {
            ZonedDateTime month = ZonedDateTime.parse(isoDate, DateTimeFormatter.ISO_ZONED_DATE_TIME);
            return service.findLessonsForTeacherForMonth(teacherId, month);
        } else return Collections.emptyList();
    }
}
