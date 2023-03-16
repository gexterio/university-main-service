package ua.com.foxminded.university.integration.presentation.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import ua.com.foxminded.university.consumer.dto.LessonDTO;

import java.util.ArrayList;
import java.util.List;

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
    void findLessonsForStudent_returnedListOfLessons_ExistsAndRangeIsDay() throws Exception {
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
    void findLessonsForStudent_returnedListOfLessons_ExistsAndRangeIsMonth() throws Exception {
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
    void findLessonsForTeacher_returnedListOfLessons_ExistsAndRangeIsDay() throws Exception {
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
    void findLessonsForTeacher_returnedListOfLessons_ExistsAndRangeISMonth() throws Exception {
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
    void findLessonsForStudent_returnedEmptyList_NotExistsAndRangeIsDay() throws Exception {
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
    void findLessonsForStudent_returnedEmptyList_NotExistsAndRangeIsMonth() throws Exception {
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
    void findLessonsForTeachers_returnedEmptyList_NotExistsAndRangeIsDay() throws Exception {
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
    void findLessonsForTeachers_returnedEmptyList_NotExistsAndRangeIsMonth() throws Exception {
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

}
