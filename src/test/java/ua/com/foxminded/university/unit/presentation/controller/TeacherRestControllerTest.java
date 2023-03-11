package ua.com.foxminded.university.unit.presentation.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ua.com.foxminded.university.consumer.dto.TeacherDTO;
import ua.com.foxminded.university.presentation.controller.TeacherRestController;
import ua.com.foxminded.university.service.TeacherService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TeacherRestController.class)
@ActiveProfiles("unitTest")
class TeacherRestControllerTest {

    @MockBean
    TeacherService service;

    @Autowired
    MockMvc mockMvc;


    @Test
    void findAll_returnedPageOfDto_Exists() throws Exception {
        List<TeacherDTO> teacherList = List.of(
                new TeacherDTO.Builder().setId(1L).setFirstName("FirstName").setLastName("LastName").build(),
                new TeacherDTO.Builder().setId(2L).setFirstName("FirstName1").setLastName("LastName1").build(),
                new TeacherDTO.Builder().setId(3L).setFirstName("FirstName2").setLastName("LastName2").build());
        Pageable pageable = PageRequest.of(0, 20);
        Page<TeacherDTO> page = new PageImpl<>(teacherList, pageable, teacherList.size());
        when(service.findAll(pageable)).thenReturn(page);
        mockMvc.perform(get("/api/v1/teachers")
                        .param("page", String.valueOf(pageable.getPageNumber()))
                        .param("size", String.valueOf(pageable.getPageSize())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(teacherList.size()))
                .andExpect(jsonPath("$.content[0].firstName").value("FirstName"))
                .andExpect(jsonPath("$.content[0].lastName").value("LastName"))
                .andExpect(jsonPath("$.content[1].firstName").value("FirstName1"))
                .andExpect(jsonPath("$.content[1].lastName").value("LastName1"))
                .andExpect(jsonPath("$.content[2].firstName").value("FirstName2"))
                .andExpect(jsonPath("$.content[2].lastName").value("LastName2"))
                .andDo(print());
    }

    @Test
    void findAll_returnedEmptyPageOfDto_NotExists() throws Exception {
        List<TeacherDTO> teacherList = new ArrayList<>();
        Pageable pageable = PageRequest.of(0, 20);
        Page<TeacherDTO> page = new PageImpl<>(teacherList, pageable, teacherList.size());
        when(service.findAll(pageable)).thenReturn(page);
        mockMvc.perform(get("/api/v1/teachers")
                        .param("page", String.valueOf(pageable.getPageNumber()))
                        .param("size", String.valueOf(pageable.getPageSize())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(teacherList.size()))
                .andDo(print());
    }
}