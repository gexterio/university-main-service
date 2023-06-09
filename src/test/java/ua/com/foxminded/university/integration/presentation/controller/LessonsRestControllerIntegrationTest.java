package ua.com.foxminded.university.integration.presentation.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.bind.MissingServletRequestParameterException;
import ua.com.foxminded.university.consumer.dto.LessonDTO;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql("classpath:data/initIntegrationTestDataScript.sql")
class LessonsRestControllerIntegrationTest extends RestControllerIntegrationTestBase {

    private static final Long id = 1L;

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
        mockMvc.perform(get("/api/v1/students/" + id + "/lessons")
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
        mockMvc.perform(get("/api/v1/students/" + id + "/lessons")
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
    @WithMockUser(username = "AndrewMoore600@email.com", password = "pass", roles = "TEACHER")
    void findLessonsForTeacher_returnedListOfLessons_existsAndRangeIsDay() throws Exception {
        mockMvc.perform(get("/api/v1/teachers/" + id + "/lessons")
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
    @WithMockUser(username = "AndrewMoore600@email.com", password = "pass", roles = "TEACHER")
    void findLessonsForTeacher_returnedListOfLessons_existsAndRangeISMonth() throws Exception {
        mockMvc.perform(get("/api/v1/teachers/" + id + "/lessons")
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
        mockMvc.perform(get("/api/v1/students/" + id + "/lessons")
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

        mockMvc.perform(get("/api/v1/students/" + id + "/lessons")
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

        mockMvc.perform(get("/api/v1/students/" + id + "/lessons")
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

        mockMvc.perform(get("/api/v1/students/" + id + "/lessons")
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
        String message = "findLessonsForStudent.range: Parameter is invalid! Valid Parameters are: day, month";

        mockMvc.perform(get("/api/v1/students/" + id + "/lessons")
                        .param("range", range)
                        .param("isoDate", ISO_DATE))
                .andExpect(status().isBadRequest())
                .andExpect(result -> Assertions.assertEquals(message, result.getResolvedException().getMessage()))
                .andDo(print());
    }

    @Test
    void findLessonsForTeacher_returnedBadRequestWithConstraintViolationException_paramRangeIsInvalid() throws Exception {
        String range = "InvalidRange";
        String message = "findLessonsForTeacher.range: Parameter is invalid! Valid Parameters are: day, month";

        mockMvc.perform(get("/api/v1/teachers/" + id + "/lessons")
                        .param("range", range)
                        .param("isoDate", ISO_DATE))
                .andExpect(status().isBadRequest())
                .andExpect(result -> Assertions.assertEquals(message, result.getResolvedException().getMessage()))
                .andDo(print());
    }

    @Test
    void findLessonsForStudent_returnedBadRequestWithConstraintViolationException_paramRangeIsNull() throws Exception {
        String range = null;

        mockMvc.perform(get("/api/v1/students/" + id + "/lessons")
                        .param("range", range)
                        .param("isoDate", ISO_DATE))
                .andExpect(status().isBadRequest())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof MissingServletRequestParameterException))
                .andDo(print());
    }

    @Test
    void findLessonsForTeacher_returnedBadRequestWithConstraintViolationException_paramRangeIsNull() throws Exception {
        String range = null;

        mockMvc.perform(get("/api/v1/teachers/" + id + "/lessons")
                        .param("range", range)
                        .param("isoDate", ISO_DATE))
                .andExpect(status().isBadRequest())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof MissingServletRequestParameterException))
                .andDo(print());
    }

    @Test
    void findLessonsForStudent_returnedBadRequestWithConstraintViolationException_paramIsoDateIsInvalid() throws Exception {
        String range = "day";
        String message = "findLessonsForStudent.isoDate: Parameter is not matches with pattern! It should look like: '2011-12-03T10:15:30+01:00[Europe/Paris]'";

        String isoDateInvalid = "20900009-02-22T14:33:02.716164+00:00";
        mockMvc.perform(get("/api/v1/students/" + id + "/lessons")
                        .param("range", range)
                        .param("isoDate", isoDateInvalid))
                .andExpect(status().isBadRequest())
                .andExpect(result -> Assertions.assertEquals(message, result.getResolvedException().getMessage()))
                .andDo(print());
    }

    @Test
    void findLessonsForTeacher_returnedBadRequestWithConstraintViolationException_paramIsoDateIsInvalid() throws Exception {
        String range = "day";
        String message = "findLessonsForTeacher.isoDate: Parameter is not matches with pattern! It should look like: '2011-12-03T10:15:30+01:00[Europe/Paris]'";

        String isoDate = "2023423499-02-22T14:33:02.716164+00:00";
        mockMvc.perform(get("/api/v1/teachers/" + id + "/lessons")
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

        mockMvc.perform(get("/api/v1/students/" + id + "/lessons")
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

        mockMvc.perform(get("/api/v1/teachers/" + id + "/lessons")
                        .param("range", range)
                        .param("isoDate", isoDate))
                .andExpect(status().isBadRequest())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof MissingServletRequestParameterException))
                .andDo(print());
    }

    @Test
    void findLessonsForStudent_statusIsOkOrElseForbidden_userRoleIsStudentWithEqualsReferenceId() throws Exception {
        String range = "month";
        String uri = String.format("/api/v1/students/%d/lessons", id);
        for (UserDetails user : testUsers) {
            if (user.getAuthorities().stream()
                    .anyMatch(auth -> "ROLE_TEACHER".equals(auth.getAuthority()))) {
                mockMvc.perform(get(uri)
                                .param("range", range)
                                .param("isoDate", ISO_DATE)
                                .with(user(user)))
                        .andExpect(status().isForbidden())
                        .andDo(print());
            } else
                mockMvc.perform(get(uri)
                                .param("range", range)
                                .param("isoDate", ISO_DATE)
                                .with(user(user)))
                        .andExpect(status().isOk())
                        .andDo(print());
        }
    }

    @Test
    void findLessonsForTeachers_statusIsOkOrElseForbidden_userRoleIsAdminOrTeacher() throws Exception {
        String range = "month";
        String uri = String.format("/api/v1/teachers/%d/lessons", id);
        for (UserDetails user : testUsers) {
            if (user.getAuthorities().stream()
                    .anyMatch(auth -> "ROLE_STUDENT".equals(auth.getAuthority()))) {
                mockMvc.perform(get(uri)
                                .param("range", range)
                                .param("isoDate", ISO_DATE)
                                .with(user(user)))
                        .andExpect(status().isForbidden())
                        .andDo(print());
            } else
                mockMvc.perform(get(uri)
                                .param("range", range)
                                .param("isoDate", ISO_DATE)
                                .with(user(user)))
                        .andExpect(status().isOk())
                        .andDo(print());
        }
    }


}
