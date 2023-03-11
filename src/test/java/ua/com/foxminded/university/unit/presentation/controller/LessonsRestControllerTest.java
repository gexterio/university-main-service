package ua.com.foxminded.university.unit.presentation.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ua.com.foxminded.university.consumer.dto.LessonDTO;
import ua.com.foxminded.university.presentation.controller.LessonsRestController;
import ua.com.foxminded.university.service.LessonService;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LessonsRestController.class)
@ActiveProfiles("unitTest")
class LessonsRestControllerTest {

    @MockBean
    LessonService service;

    @Autowired
    MockMvc mockMvc;

    @Test
    void findLessonsForStudent_returnedListOfLessons_ExistsAndRangeIsDay() throws Exception {
        String isoDate = "2023-02-13T14:53:34.396+00:00";
        ZonedDateTime date = ZonedDateTime.parse(isoDate, DateTimeFormatter.ISO_ZONED_DATE_TIME);
        Long id = 1L;
        List<LessonDTO> lessonsForDay = List.of(
                new LessonDTO.Builder().setId(1L).setName("LessonOne").build()
        );
        when(service.findLessonsForStudentForDay(id, date)).thenReturn(lessonsForDay);

        mockMvc.perform(get("/api/v1/students/" + id + "/lessons")
                        .param("range", "day")
                        .param("isoDate", isoDate))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(lessonsForDay.size()))
                .andExpect(jsonPath("$.[0].id").value("1"))
                .andExpect(jsonPath("$.[0].name").value("LessonOne"))
                .andDo(print());
    }

    @Test
    void findLessonsForStudent_returnedListOfLessons_ExistsAndRangeIsMonth() throws Exception {
        String isoDate = "2023-02-13T14:53:34.396+00:00";
        ZonedDateTime date = ZonedDateTime.parse(isoDate, DateTimeFormatter.ISO_ZONED_DATE_TIME);
        Long id = 1L;
        List<LessonDTO> lessonsForMonth = List.of(
                new LessonDTO.Builder().setId(1L).setName("LessonOne").build(),
                new LessonDTO.Builder().setId(2L).setName("LessonTwo").build(),
                new LessonDTO.Builder().setId(3L).setName("LessonThree").build()
        );
        when(service.findLessonsForStudentForMonth(id, date)).thenReturn(lessonsForMonth);

        mockMvc.perform(get("/api/v1/students/" + id + "/lessons")
                        .param("range", "month")
                        .param("isoDate", isoDate))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(lessonsForMonth.size()))
                .andExpect(jsonPath("$.[0].id").value("1"))
                .andExpect(jsonPath("$.[0].name").value("LessonOne"))
                .andExpect(jsonPath("$.[1].id").value("2"))
                .andExpect(jsonPath("$.[1].name").value("LessonTwo"))
                .andExpect(jsonPath("$.[2].id").value("3"))
                .andExpect(jsonPath("$.[2].name").value("LessonThree"))
                .andDo(print());
    }

    @Test
    void findLessonsForTeacher_returnedListOfLessons_ExistsAndRangeIsDay() throws Exception {
        String isoDate = "2023-02-13T14:53:34.396+00:00";
        ZonedDateTime date = ZonedDateTime.parse(isoDate, DateTimeFormatter.ISO_ZONED_DATE_TIME);
        Long id = 1L;
        List<LessonDTO> lessonsForDay = List.of(
                new LessonDTO.Builder().setId(1L).setName("LessonOne").build()
        );
        when(service.findLessonsForTeacherForDay(id, date)).thenReturn(lessonsForDay);

        mockMvc.perform(get("/api/v1/teachers/" + id + "/lessons")
                        .param("range", "day")
                        .param("isoDate", isoDate))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(lessonsForDay.size()))
                .andExpect(jsonPath("$.[0].id").value("1"))
                .andExpect(jsonPath("$.[0].name").value("LessonOne"))
                .andDo(print());
    }

    @Test
    void findLessonsForTeacher_returnedListOfLessons_ExistsAndRangeISMonth() throws Exception {
        String isoDate = "2023-02-13T14:53:34.396+00:00";
        ZonedDateTime date = ZonedDateTime.parse(isoDate, DateTimeFormatter.ISO_ZONED_DATE_TIME);
        Long id = 1L;
        List<LessonDTO> lessonsForMonth = List.of(
                new LessonDTO.Builder().setId(1L).setName("LessonOne").build(),
                new LessonDTO.Builder().setId(2L).setName("LessonTwo").build(),
                new LessonDTO.Builder().setId(3L).setName("LessonThree").build()
        );
        when(service.findLessonsForTeacherForMonth(id, date)).thenReturn(lessonsForMonth);

        mockMvc.perform(get("/api/v1/teachers/" + id + "/lessons")
                        .param("range", "month")
                        .param("isoDate", isoDate))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(lessonsForMonth.size()))
                .andExpect(jsonPath("$.[0].id").value("1"))
                .andExpect(jsonPath("$.[0].name").value("LessonOne"))
                .andExpect(jsonPath("$.[1].id").value("2"))
                .andExpect(jsonPath("$.[1].name").value("LessonTwo"))
                .andExpect(jsonPath("$.[2].id").value("3"))
                .andExpect(jsonPath("$.[2].name").value("LessonThree"))
                .andDo(print());
    }

    @Test
    void findLessonsForStudent_returnedEmptyList_NotExistsAndRangeIsDay() throws Exception {
        String isoDate = "2023-02-13T14:53:34.396+00:00";
        ZonedDateTime date = ZonedDateTime.parse(isoDate, DateTimeFormatter.ISO_ZONED_DATE_TIME);
        Long id = 1L;
        List<LessonDTO> lessonsForDay = new ArrayList<>();
        when(service.findLessonsForStudentForDay(id, date)).thenReturn(lessonsForDay);

        mockMvc.perform(get("/api/v1/students/" + id + "/lessons")
                        .param("range", "day")
                        .param("isoDate", isoDate))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(lessonsForDay.size()))
                .andDo(print());
    }

    @Test
    void findLessonsForStudent_returnedEmptyList_NotExistsAndRangeIsMonth() throws Exception {
        String isoDate = "2023-02-13T14:53:34.396+00:00";
        ZonedDateTime date = ZonedDateTime.parse(isoDate, DateTimeFormatter.ISO_ZONED_DATE_TIME);
        Long id = 1L;
        List<LessonDTO> lessonsForMonth = new ArrayList<>();
        when(service.findLessonsForStudentForMonth(id, date)).thenReturn(lessonsForMonth);

        mockMvc.perform(get("/api/v1/students/" + id + "/lessons")
                        .param("range", "month")
                        .param("isoDate", isoDate))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(lessonsForMonth.size()))
                .andDo(print());
    }

    @Test
    void findLessonsForTeachers_returnedEmptyList_NotExistsAndRangeIsDay() throws Exception {
        String isoDate = "2023-02-13T14:53:34.396+00:00";
        ZonedDateTime date = ZonedDateTime.parse(isoDate, DateTimeFormatter.ISO_ZONED_DATE_TIME);
        Long id = 1L;
        List<LessonDTO> lessonsForDay = new ArrayList<>();
        when(service.findLessonsForTeacherForDay(id, date)).thenReturn(lessonsForDay);

        mockMvc.perform(get("/api/v1/students/" + id + "/lessons")
                        .param("range", "day")
                        .param("isoDate", isoDate))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(lessonsForDay.size()))
                .andDo(print());
    }


    @Test
    void findLessonsForTeachers_returnedEmptyList_NotExistsAndRangeIsMonth() throws Exception {
        String isoDate = "2023-02-13T14:53:34.396+00:00";
        ZonedDateTime date = ZonedDateTime.parse(isoDate, DateTimeFormatter.ISO_ZONED_DATE_TIME);
        Long id = 1L;
        List<LessonDTO> lessonsForMonth = new ArrayList<>();
        when(service.findLessonsForTeacherForMonth(id, date)).thenReturn(lessonsForMonth);

        mockMvc.perform(get("/api/v1/students/" + id + "/lessons")
                        .param("range", "month")
                        .param("isoDate", isoDate))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(lessonsForMonth.size()))
                .andDo(print());
    }


}