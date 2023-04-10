package ua.com.foxminded.university.integration.presentation.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import ua.com.foxminded.university.consumer.dto.StudentDTO;
import ua.com.foxminded.university.consumer.exception.StudentAlreadyExistException;
import ua.com.foxminded.university.consumer.exception.StudentNotFoundException;

import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
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
    private final Long notExistId = 999999L;

    @Test
    void findAll_returnedPageOfStudents_exists() throws Exception {
        List<StudentDTO> studentList = List.of(
                new StudentDTO.Builder().setId(id).setFirstName("John").setLastName("Smith").build(),
                new StudentDTO.Builder().setId(id + 1).setFirstName("Daniel").setLastName("Lopez").build()
        );
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
                .andDo(print());
    }

    @Test
    void findById_returnedStudent_exists() throws Exception {
        StudentDTO student = new StudentDTO.Builder().setId(id).setFirstName("John").setLastName("Smith").build();
        String uri = String.format("/api/v1/students/%d", student.getId());
        mockMvc.perform(get(uri))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(student.getId()))
                .andExpect(jsonPath("$.firstName").value(student.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(student.getLastName()))
                .andDo(print());
    }

    @Test
    void findById_returnedException_notExists() throws Exception {
        String uri = String.format("/api/v1/students/%d", notExistId);
        mockMvc.perform(get(uri))
                .andExpect(status().isNotFound())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof StudentNotFoundException))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON
                ))
                .andDo(print());
    }

    @Test
    void create_returnedStudentAndPutItIntoDataBase_inputIsValidStudentDTO() throws Exception {
        StudentDTO student = new StudentDTO.Builder().setFirstName("Bob").setLastName("Morley").setAge((byte) 19).setCourse((byte) 1).build();
        String uri = "/api/v1/students";
        mockMvc.perform(post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(student))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(4))
                .andExpect(jsonPath("$.firstName").value(student.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(student.getLastName()))
                .andDo(print());
    }

    @Test
    void create_returnedException_alreadyExistStudent() throws Exception {
        StudentDTO student = new StudentDTO.Builder().setId(id).setFirstName("Bob").setLastName("Morley").setAge((byte) 19).setCourse((byte) 1).build();
        String uri = "/api/v1/students";
        mockMvc.perform(post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(student))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof StudentAlreadyExistException))
                .andDo(print());
    }

    @Test
    void update_returnedUpdatedStudent_exists() throws Exception {
        StudentDTO student = new StudentDTO.Builder().setId(id).setFirstName("NewName").setLastName("NewLastName").setAge((byte) 19).setCourse((byte) 1).build();
        String uri = String.format("/api/v1/students/%d", student.getId());

        mockMvc.perform(put(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(student))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void update_returnedException_notExists() throws Exception {
        StudentDTO student = new StudentDTO.Builder().setId(notExistId).setFirstName("NewName").setLastName("NewLastName").setAge((byte) 19).setCourse((byte) 1).build();
        String uri = String.format("/api/v1/students/%d", student.getId());

        mockMvc.perform(put(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(student))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof StudentNotFoundException))
                .andDo(print());
    }

    @Test
    void delete_responseStatusCode204_exists() throws Exception {
        String uri = String.format("/api/v1/students/%d", id);

        mockMvc.perform(delete(uri))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    void delete_returnedException_exists() throws Exception {
        String uri = String.format("/api/v1/students/%d", notExistId);

        mockMvc.perform(delete(uri))
                .andExpect(status().isNotFound())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof StudentNotFoundException))
                .andDo(print());
    }

    @Test
    void findAll_statusIsOkOrElseForbidden_userRoleIsAdminOrTeacher() throws Exception {
        Pageable pageable = PageRequest.of(0, 20);
        String uri = "/api/v1/students";
        for (UserDetails user : testUsers) {
            if (user.getAuthorities().stream()
                    .anyMatch(auth -> "ROLE_STUDENT".equals(auth.getAuthority()))) {
                mockMvc.perform(get(uri)
                                .param("page", "" + pageable.getPageNumber())
                                .param("size", "" + pageable.getPageSize())
                                .with(user(user)))
                        .andExpect(status().isForbidden())
                        .andDo(print());
            } else
                mockMvc.perform(get(uri)
                                .param("page", "" + pageable.getPageNumber())
                                .param("size", "" + pageable.getPageSize())
                                .with(user(user)))
                        .andExpect(status().isOk())
                        .andDo(print());
        }
    }

    @Test
    void findById_statusIsOkOrElseForbidden_userRoleIsAdmin() throws Exception {
        String uri = String.format("/api/v1/students/%d", id);
        for (UserDetails user : testUsers) {
            if (user.getAuthorities().stream()
                    .anyMatch(auth -> "ROLE_ADMIN".equals(auth.getAuthority()))) {
                mockMvc.perform(get(uri)
                                .with(user(user)))
                        .andExpect(status().isOk())
                        .andDo(print());
            } else
                mockMvc.perform(get(uri)
                                .with(user(user)))
                        .andExpect(status().isForbidden())
                        .andDo(print());
        }
    }

    @Test
    void create_statusIsCreatedOrElseForbidden_userRoleIsAdmin() throws Exception {
        StudentDTO student = new StudentDTO.Builder().setFirstName("Bob").setLastName("Morley").setAge((byte) 22).setCourse((byte) 1).build();
        String uri = "/api/v1/students";
        for (UserDetails user : testUsers) {
            if (user.getAuthorities().stream()
                    .anyMatch(auth -> "ROLE_ADMIN".equals(auth.getAuthority()))) {
                mockMvc.perform(post(uri)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(student))
                                .accept(MediaType.APPLICATION_JSON)
                                .with(user(user)))
                        .andExpect(status().isCreated())
                        .andDo(print());
            } else
                mockMvc.perform(post(uri)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(student))
                                .accept(MediaType.APPLICATION_JSON)
                                .with(user(user)))
                        .andExpect(status().isForbidden())
                        .andDo(print());
        }
    }

    @Test
    void update_statusIsOkOrElseForbidden_userRoleIsAdmin() throws Exception {
        StudentDTO student = new StudentDTO.Builder().setId(1L).setFirstName("Bob").setLastName("Morley").setAge((byte) 22).setCourse((byte) 1).build();
        String uri = String.format("/api/v1/students/%d", student.getId());
        for (UserDetails user : testUsers) {
            if (user.getAuthorities().stream()
                    .anyMatch(auth -> "ROLE_ADMIN".equals(auth.getAuthority()))) {
                mockMvc.perform(put(uri)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(student))
                                .accept(MediaType.APPLICATION_JSON)
                                .with(user(user)))
                        .andExpect(status().isOk())
                        .andDo(print());
            } else
                mockMvc.perform(put(uri)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(student))
                                .accept(MediaType.APPLICATION_JSON)
                                .with(user(user)))
                        .andExpect(status().isForbidden())
                        .andDo(print());
        }
    }

    @Test
    void delete_statusIsOkOrElseForbidden_userRoleIsAdmin() throws Exception {
        String uri = String.format("/api/v1/students/%d", id);
        for (UserDetails user : testUsers) {
            if (user.getAuthorities().stream()
                    .anyMatch(auth -> "ROLE_ADMIN".equals(auth.getAuthority()))) {
                mockMvc.perform(delete(uri)
                                .with(user(user)))
                        .andExpect(status().isNoContent())
                        .andDo(print());
            } else
                mockMvc.perform(get(uri)
                                .with(user(user)))
                        .andExpect(status().isForbidden())
                        .andDo(print());
        }
    }

}
