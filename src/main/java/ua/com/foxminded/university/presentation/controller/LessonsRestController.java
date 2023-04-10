package ua.com.foxminded.university.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.foxminded.university.consumer.dto.LessonDTO;
import ua.com.foxminded.university.consumer.service.LessonService;
import ua.com.foxminded.university.util.validation.TimeRange;
import ua.com.foxminded.university.util.validation.ZonedDateTimePattern;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

@Schema(description = "The controller of the Lessons")
@RestController
@RequestMapping("/api/v1")
@Validated
public class LessonsRestController {

    private final LessonService service;

    @Autowired
    public LessonsRestController(LessonService service) {
        this.service = service;
    }

    @Operation(summary = "The get operation for lessons",
            description = "The get operations for lessons for current student in the specified range",
            parameters = {
                    @Parameter(name = "id", description = "The id of Student", example = "10"),
                    @Parameter(name = "range", description = "The lessons search range", example = "month"),
                    @Parameter(name = "isoDate", description = "the date from which to search for lessons in the range", example = "2023-02-21T20:33:02.716164+00:00")},
            responses = {
                    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json")}, description = "Operation execute successfully"),
            })
    @GetMapping("/students/{id}/lessons")
    @PreAuthorize("hasRole('ADMIN') or @studentPersonalInfoSecurityChecker.checkStudentId(authentication,#id)")
    public List<LessonDTO> findLessonsForStudent(@PathVariable("id") Long id,
                                                 @RequestParam("range") @TimeRange String range,
                                                 @RequestParam("isoDate")
                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @ZonedDateTimePattern String isoDate) {
        if ("day".equals(range)) {
            ZonedDateTime day = ZonedDateTime.parse(isoDate, DateTimeFormatter.ISO_ZONED_DATE_TIME);
            return service.findLessonsForStudentForDay(id, day);
        } else if ("month".equals(range)) {
            ZonedDateTime month = ZonedDateTime.parse(isoDate, DateTimeFormatter.ISO_ZONED_DATE_TIME);
            return service.findLessonsForStudentForMonth(id, month);
        } else return Collections.emptyList();
    }

    @Operation(summary = "The get operation for lessons",
            description = "The get operations for lessons for current teacher in the specified range",
            parameters = {
                    @Parameter(name = "id", description = "The id of Teacher", example = "10"),
                    @Parameter(name = "range", description = "The lessons search range", example = "month"),
                    @Parameter(name = "isoDate", description = "the date from which to search for lessons in the range", example = "2023-02-21T20:33:02.716164+00:00")},
            responses = {
                    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json")}, description = "Operation execute successfully"),
            })
    @GetMapping("/teachers/{id}/lessons")
    public List<LessonDTO> findLessonsForTeacher(@PathVariable("id") Long id,
                                                 @RequestParam("range") @TimeRange String range,
                                                 @RequestParam("isoDate")
                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @ZonedDateTimePattern String isoDate) {
        if ("day".equals(range)) {
            ZonedDateTime day = ZonedDateTime.parse(isoDate, DateTimeFormatter.ISO_ZONED_DATE_TIME);
            return service.findLessonsForTeacherForDay(id, day);
        } else if ("month".equals(range)) {
            ZonedDateTime month = ZonedDateTime.parse(isoDate, DateTimeFormatter.ISO_ZONED_DATE_TIME);
            return service.findLessonsForTeacherForMonth(id, month);
        } else return Collections.emptyList();
    }
}
