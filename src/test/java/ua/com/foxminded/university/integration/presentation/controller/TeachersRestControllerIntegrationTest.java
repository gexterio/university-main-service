package ua.com.foxminded.university.integration.presentation.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import ua.com.foxminded.university.consumer.dto.TeacherDTO;
 import ua.com.foxminded.university.consumer.exception.TeacherNotFoundException;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TeachersRestControllerIntegrationTest extends RestControllerIntegrationTestBase {

    private final Long id = 1L;
    private final Long notExistId = 999999L;

    @Test
    void findAll_returnedPageOfTeachers_exists() throws Exception {
        List<TeacherDTO> teacherList = List.of(
                new TeacherDTO.Builder().setId(id).setFirstName("Andrew").setLastName("Moore").build(),
                new TeacherDTO.Builder().setId(id + 1).setFirstName("Paul").setLastName("Garcia").build(),
                new TeacherDTO.Builder().setId(id + 2).setFirstName("Robert").setLastName("Williams").build());
        Pageable pageable = PageRequest.of(0, 20);
        String uri = "/api/v1/teachers";
        mockMvc.perform(get(uri)
                        .param("page", "" + pageable.getPageNumber())
                        .param("size", "" + pageable.getPageSize()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content.length()").value(teacherList.size()))
                .andExpect(jsonPath("$.content[0].firstName").value(teacherList.get(0).getFirstName()))
                .andExpect(jsonPath("$.content[0].lastName").value(teacherList.get(0).getLastName()))
                .andExpect(jsonPath("$.content[1].firstName").value(teacherList.get(1).getFirstName()))
                .andExpect(jsonPath("$.content[1].lastName").value(teacherList.get(1).getLastName()))
                .andExpect(jsonPath("$.content[2].firstName").value(teacherList.get(2).getFirstName()))
                .andExpect(jsonPath("$.content[2].lastName").value(teacherList.get(2).getLastName()))
                .andDo(print());
    }

    @Test
    void findById_returnedTeacher_exists() throws Exception {
        TeacherDTO teacher = new TeacherDTO.Builder().setId(id).setFirstName("Andrew").setLastName("Moore").setAge((byte) 22).build();
        String uri = String.format("/api/v1/teachers/%d", teacher.getId());
        mockMvc.perform(get(uri))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(teacher.getId()))
                .andExpect(jsonPath("$.firstName").value(teacher.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(teacher.getLastName()))
                .andDo(print());
    }

    @Test
    void findById_returnedException_notExists() throws Exception {
        String uri = String.format("/api/v1/teachers/%d", notExistId);
        mockMvc.perform(get(uri))
                .andExpect(status().isNotFound())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof TeacherNotFoundException))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @Test
    void create_returnedTeacherAndPutItIntoDataBase_inputIsValidTeacherDTO() throws Exception {
        TeacherDTO teacher = new TeacherDTO.Builder().setFirstName("Bob").setLastName("Morley").setAge((byte) 22).build();
        String uri = "/api/v1/teachers";
        mockMvc.perform(post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(teacher))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.firstName").value(teacher.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(teacher.getLastName()))
                .andDo(print());
    }

    @Test
    void create_returnedException_alreadyExistTeacher() throws Exception {
        TeacherDTO teacher = new TeacherDTO.Builder().setId(id).setFirstName("Bob").setLastName("Morley").setAge((byte) 22).build();
        String uri = "/api/v1/teachers";
        mockMvc.perform(post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(teacher))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @Test
    void update_returnedUpdatedTeacher_exists() throws Exception {
        TeacherDTO teacher = new TeacherDTO.Builder().setId(id).setFirstName("NewName").setLastName("NewLastName").setAge((byte) 22).build();
        String uri = String.format("/api/v1/teachers/%d", teacher.getId());

        mockMvc.perform(put(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(teacher))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void update_returnedException_notExists() throws Exception {
        TeacherDTO teacher = new TeacherDTO.Builder().setId(notExistId).setFirstName("NewName").setLastName("NewLastName").setAge((byte) 22).build();
        String uri = String.format("/api/v1/teachers/%d", teacher.getId());

        mockMvc.perform(put(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(teacher))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof TeacherNotFoundException))
                .andDo(print());
    }

    @Test
    void delete_responseStatusCode204_exists() throws Exception {
        String uri = String.format("/api/v1/teachers/%d", id);

        mockMvc.perform(delete(uri))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    void delete_returnedException_exists() throws Exception {
        String uri = String.format("/api/v1/teachers/%d", notExistId);

        mockMvc.perform(delete(uri))
                .andExpect(status().isNotFound())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof TeacherNotFoundException))
                .andDo(print());
    }

}
