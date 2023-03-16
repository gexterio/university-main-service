package ua.com.foxminded.university.integration.presentation.controller;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import ua.com.foxminded.university.consumer.dto.StudentDTO;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class StudentsRestControllerIntegrationTest extends RestControllerIntegrationTestBase {

    private final Long id = 1L;

    @Test
    void findAll_returnedPageOfStudents_Exists() throws Exception {
        List<StudentDTO> studentList = List.of(
                new StudentDTO.Builder().setId(id).setFirstName("John").setLastName("Smith").build(),
                new StudentDTO.Builder().setId(id + 1).setFirstName("Daniel").setLastName("Lopez").build(),
                new StudentDTO.Builder().setId(id + 2).setFirstName("Anthony").setLastName("Moore").build());
        Pageable pageable = PageRequest.of(0, 20);
        String uri = "/api/v1/students";
        mockMvc.perform(get(uri)
                        .param("page", "" + pageable.getPageNumber())
                        .param("size", "" + pageable.getPageSize()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content.length()").value(studentList.size()))
                .andExpect(jsonPath("$.content[0].firstName").value(studentList.get(0).getFirstName()))
                .andExpect(jsonPath("$.content[0].lastName").value(studentList.get(0).getLastName()))
                .andExpect(jsonPath("$.content[1].firstName").value(studentList.get(1).getFirstName()))
                .andExpect(jsonPath("$.content[1].lastName").value(studentList.get(1).getLastName()))
                .andExpect(jsonPath("$.content[2].firstName").value(studentList.get(2).getFirstName()))
                .andExpect(jsonPath("$.content[2].lastName").value(studentList.get(2).getLastName()))
                .andDo(print());
    }

    @Test
    void findById_returnedStudent_Exists() throws Exception {
        StudentDTO student = new StudentDTO.Builder().setId(id).setFirstName("John").setLastName("Smith").build();
        String uri = String.format("/api/v1/students/%d", id);
        mockMvc.perform(get(uri))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(student.getId()))
                .andExpect(jsonPath("$.firstName").value(student.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(student.getLastName()))
                .andDo(print());
    }

    @Test
    void create_returnedStudentAndPutItIntoDataBase_InputIsValidStudentDTO() throws Exception {
        StudentDTO student = new StudentDTO.Builder().setFirstName("Bob").setLastName("Morley").build();
        String uri = "/api/v1/students";
        mockMvc.perform(post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(student))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.firstName").value(student.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(student.getLastName()))
                .andDo(print());
    }

    @Test
    void update_returnedUpdatedStudent_Exists() throws Exception {
        StudentDTO student = new StudentDTO.Builder().setId(id).setFirstName("NewName").setLastName("NewLastName").build();
        String uri = String.format("/api/v1/students/%d", id);

        mockMvc.perform(put(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(student))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void delete_responseStatusCode204_Exists() throws Exception {
        String uri = String.format("/api/v1/students/%d", id);

        mockMvc.perform(delete(uri))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

}
