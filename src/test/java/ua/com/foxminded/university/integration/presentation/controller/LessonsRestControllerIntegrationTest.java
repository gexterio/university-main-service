package ua.com.foxminded.university.integration.presentation.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.bind.MissingServletRequestParameterException;
import ua.com.foxminded.university.consumer.dto.LessonDTO;
import ua.com.foxminded.university.util.validation.TimeRange;
import ua.com.foxminded.university.util.validation.ZonedDateTimePattern;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql("classpath:data/initIntegrationTestDataScript.sql")
class LessonsRestControllerIntegrationTest extends RestControllerIntegrationTestBase {

    private static final Long ID = 1L;

    private static final String ISO_DATE = "2023-02-22T14:33:02.716164+00:00";

    List<LessonDTO> lessonsForDay = List.of(
            new LessonDTO.Builder().setId(1L).setName("Web Development Fundamentals").build()
    );

    List<LessonDTO> lessonsForMonth = List.of(
            new LessonDTO.Builder().setId(1L).setName("Web Development Fundamentals").build(),
            new LessonDTO.Builder().setId(2L).setName("Introduction to Computer Programming").build()
    );

    @Test
    void findLessonsForStudent_returnedListOfLessons_existsAndRangeIsDay() throws Exception {
        mockMvc.perform(get("/api/v1/students/" + ID + "/lessons")
                        .param("range", "day")
                        .param("isoDate", ISO_DATE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(lessonsForDay.size()))
                .andExpect(jsonPath("$.[0].id").value(lessonsForDay.get(0).getId()))
                .andExpect(jsonPath("$.[0].name").value(lessonsForDay.get(0).getName()))
                .andDo(print());
    }

    @Test
    void findLessonsForStudent_returnedListOfLessons_existsAndRangeIsMonth() throws Exception {
        mockMvc.perform(get("/api/v1/students/" + ID + "/lessons")
                        .param("range", "month")
                        .param("isoDate", ISO_DATE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(lessonsForMonth.size()))
                .andExpect(jsonPath("$.[0].id").value(lessonsForMonth.get(0).getId()))
                .andExpect(jsonPath("$.[0].name").value(lessonsForMonth.get(0).getName()))
                .andExpect(jsonPath("$.[1].id").value(lessonsForMonth.get(1).getId()))
                .andExpect(jsonPath("$.[1].name").value(lessonsForMonth.get(1).getName()))
                .andDo(print());
    }

    @Test
    void findLessonsForTeacher_returnedListOfLessons_existsAndRangeIsDay() throws Exception {
        mockMvc.perform(get("/api/v1/teachers/" + ID + "/lessons")
                        .param("range", "day")
                        .param("isoDate", ISO_DATE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(lessonsForDay.size()))
                .andExpect(jsonPath("$.[0].id").value(lessonsForDay.get(0).getId()))
                .andExpect(jsonPath("$.[0].name").value(lessonsForDay.get(0).getName()))
                .andDo(print());
    }

    @Test
    void findLessonsForTeacher_returnedListOfLessons_existsAndRangeISMonth() throws Exception {
        mockMvc.perform(get("/api/v1/teachers/" + ID + "/lessons")
                        .param("range", "month")
                        .param("isoDate", ISO_DATE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(lessonsForMonth.size()))
                .andExpect(jsonPath("$.[0].id").value(lessonsForMonth.get(0).getId()))
                .andExpect(jsonPath("$.[0].name").value(lessonsForMonth.get(0).getName()))
                .andExpect(jsonPath("$.[1].id").value(lessonsForMonth.get(1).getId()))
                .andExpect(jsonPath("$.[1].name").value(lessonsForMonth.get(1).getName()))
                .andDo(print());
    }

    @Test
    void findLessonsForStudent_returnedEmptyList_notExistsAndRangeIsDay() throws Exception {
        List<LessonDTO> lessonsForDay = new ArrayList<>();
        String emptyDate = "2099-02-22T14:33:02.716164+00:00";
        mockMvc.perform(get("/api/v1/students/" + ID + "/lessons")
                        .param("range", "day")
                        .param("isoDate", emptyDate))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(lessonsForDay.size()))
                .andDo(print());
    }

    @Test
    void findLessonsForStudent_returnedEmptyList_notExistsAndRangeIsMonth() throws Exception {
        List<LessonDTO> lessonsForMonth = new ArrayList<>();
        String emptyDate = "2099-02-22T14:33:02.716164+00:00";

        mockMvc.perform(get("/api/v1/students/" + ID + "/lessons")
                        .param("range", "month")
                        .param("isoDate", emptyDate))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(lessonsForMonth.size()))
                .andDo(print());
    }

    @Test
    void findLessonsForTeachers_returnedEmptyList_notExistsAndRangeIsDay() throws Exception {
        List<LessonDTO> lessonsForDay = new ArrayList<>();
        String emptyDate = "2099-02-22T14:33:02.716164+00:00";

        mockMvc.perform(get("/api/v1/students/" + ID + "/lessons")
                        .param("range", "day")
                        .param("isoDate", emptyDate))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(lessonsForDay.size()))
                .andDo(print());
    }


    @Test
    void findLessonsForTeachers_returnedEmptyList_notExistsAndRangeIsMonth() throws Exception {
        List<LessonDTO> lessonsForMonth = new ArrayList<>();
        String emptyDate = "2099-02-22T14:33:02.716164+00:00";

        mockMvc.perform(get("/api/v1/students/" + ID + "/lessons")
                        .param("range", "month")
                        .param("isoDate", emptyDate))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(lessonsForMonth.size()))
                .andDo(print());
    }

    @Test
    void findLessonsForStudent_returnedBadRequestWithConstraintViolationException_paramRangeIsInvalid() throws Exception {
        String range = "InvalidRange";
        Optional<Method> optional = Arrays.stream(TimeRange.class.getMethods()).filter(m -> m.getName().equals("message")).findFirst();
        String message = "findLessonsForStudent.range: " + optional.get().getDefaultValue();

        mockMvc.perform(get("/api/v1/students/" + ID + "/lessons")
                        .param("range", range)
                        .param("isoDate", ISO_DATE))
                .andExpect(status().isBadRequest())
                .andExpect(result -> Assertions.assertEquals(message, result.getResolvedException().getMessage()))
                .andDo(print());
    }

    @Test
    void findLessonsForTeacher_returnedBadRequestWithConstraintViolationException_paramRangeIsInvalid() throws Exception {
        String range = "InvalidRange";
        Optional<Method> optional = Arrays.stream(TimeRange.class.getMethods()).filter(m -> m.getName().equals("message")).findFirst();
        String message = "findLessonsForTeacher.range: " + optional.get().getDefaultValue();

        mockMvc.perform(get("/api/v1/teachers/" + ID + "/lessons")
                        .param("range", range)
                        .param("isoDate", ISO_DATE))
                .andExpect(status().isBadRequest())
                .andExpect(result -> Assertions.assertEquals(message, result.getResolvedException().getMessage()))
                .andDo(print());
    }

    @Test
    void findLessonsForStudent_returnedBadRequestWithConstraintViolationException_paramRangeIsNull() throws Exception {
        String range = null;

        mockMvc.perform(get("/api/v1/students/" + ID + "/lessons")
                        .param("range", range)
                        .param("isoDate", ISO_DATE))
                .andExpect(status().isBadRequest())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof MissingServletRequestParameterException))
                .andDo(print());
    }

    @Test
    void findLessonsForTeacher_returnedBadRequestWithConstraintViolationException_paramRangeIsNull() throws Exception {
        String range = null;

        mockMvc.perform(get("/api/v1/teachers/" + ID + "/lessons")
                        .param("range", range)
                        .param("isoDate", ISO_DATE))
                .andExpect(status().isBadRequest())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof MissingServletRequestParameterException))
                .andDo(print());
    }

    @Test
    void findLessonsForStudent_returnedBadRequestWithConstraintViolationException_paramIsoDateIsInvalid() throws Exception {
        String range = "day";
        Optional<Method> optional = Arrays.stream(ZonedDateTimePattern.class.getMethods()).filter(m -> m.getName().equals("message")).findFirst();
        String message = "findLessonsForStudent.isoDate: " + optional.get().getDefaultValue();

        String isoDateInvalid = "20900009-02-22T14:33:02.716164+00:00";
        mockMvc.perform(get("/api/v1/students/" + ID + "/lessons")
                        .param("range", range)
                        .param("isoDate", isoDateInvalid))
                .andExpect(status().isBadRequest())
                .andExpect(result -> Assertions.assertEquals(message, result.getResolvedException().getMessage()))
                .andDo(print());
    }

    @Test
    void findLessonsForTeacher_returnedBadRequestWithConstraintViolationException_paramIsoDateIsInvalid() throws Exception {
        String range = "day";
        Optional<Method> optional = Arrays.stream(ZonedDateTimePattern.class.getMethods()).filter(m -> m.getName().equals("message")).findFirst();
        String message = "findLessonsForTeacher.isoDate: " + optional.get().getDefaultValue();

        String isoDate = "2023423499-02-22T14:33:02.716164+00:00";
        mockMvc.perform(get("/api/v1/teachers/" + ID + "/lessons")
                        .param("range", range)
                        .param("isoDate", isoDate))
                .andExpect(status().isBadRequest())
                .andExpect(result -> Assertions.assertEquals(message, result.getResolvedException().getMessage()))
                .andDo(print());
    }

    @Test
    void findLessonsForStudent_returnedBadRequestWithConstraintViolationException_paramIsoDateIsNull() throws Exception {
        String range = "day";
        String isoDate = null;

        mockMvc.perform(get("/api/v1/students/" + ID + "/lessons")
                        .param("range", range)
                        .param("isoDate", isoDate))
                .andExpect(status().isBadRequest())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof MissingServletRequestParameterException))
                .andDo(print());
    }

    @Test
    void findLessonsForTeacher_returnedBadRequestWithConstraintViolationException_paramIsoDateIsNull() throws Exception {
        String range = "day";
        String isoDate = null;

        mockMvc.perform(get("/api/v1/teachers/" + ID + "/lessons")
                        .param("range", range)
                        .param("isoDate", isoDate))
                .andExpect(status().isBadRequest())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof MissingServletRequestParameterException))
                .andDo(print());
    }
}
