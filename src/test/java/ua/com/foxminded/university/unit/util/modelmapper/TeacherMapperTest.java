package ua.com.foxminded.university.unit.util.modelmapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.foxminded.university.consumer.dto.FacultyDTO;
import ua.com.foxminded.university.consumer.dto.TeacherDTO;
import ua.com.foxminded.university.unit.persistance.annotation.UnitTest;
import ua.com.foxminded.university.persistance.model.FacultyEntity;
import ua.com.foxminded.university.persistance.model.TeacherEntity;
import ua.com.foxminded.university.util.modelmapper.TeacherMapper;

@UnitTest
class TeacherMapperTest {

    @Autowired
    TeacherMapper teacherMapper;

    @Test
    void toEntity_returnEntity_inputNotNull() {
        TeacherDTO dto = new TeacherDTO.Builder()
                .setId(1L)
                .setFirstName("firstName")
                .setLastName("lastName")
                .setAge((byte) 66)
                .setEmail("email")
                .setExperience(40)
                .setGrade("testGrade")
                .setFaculty(new FacultyDTO.Builder().setName("faculty").build())
                .build();
        TeacherEntity entity = teacherMapper.toEntity(dto);
        Assertions.assertEquals(dto.getId(), entity.getId());
        Assertions.assertEquals(dto.getFirstName(), entity.getFirstName());
        Assertions.assertEquals(dto.getLastName(), entity.getLastName());
        Assertions.assertEquals(dto.getAge(), entity.getAge());
        Assertions.assertEquals(dto.getEmail(), entity.getEmail());
        Assertions.assertEquals(dto.getExperience(), entity.getExperience());
        Assertions.assertEquals(dto.getGrade(), entity.getGrade());
        Assertions.assertEquals(dto.getFaculty().getName(), entity.getFaculty().getName());
    }


    @Test
    void toDto_returnDto_inputNotNull() {
        TeacherEntity entity = new TeacherEntity.Builder("firstName", "lastName")
                .setId(1L)
                .setAge((byte) 66)
                .setEmail("email")
                .setExperience(40)
                .setGrade("testGrade")
                .setFaculty(new FacultyEntity.Builder("faculty").build())
                .build();
        TeacherDTO dto = teacherMapper.toDto(entity);
        Assertions.assertEquals(entity.getId(), dto.getId());
        Assertions.assertEquals(entity.getFirstName(), dto.getFirstName());
        Assertions.assertEquals(entity.getLastName(), dto.getLastName());
        Assertions.assertEquals(entity.getAge(), dto.getAge());
        Assertions.assertEquals(entity.getEmail(), dto.getEmail());
        Assertions.assertEquals(entity.getExperience(), dto.getExperience());
        Assertions.assertEquals(entity.getGrade(), dto.getGrade());
        Assertions.assertEquals(entity.getFaculty().getName(), (dto.getFaculty().getName()));
    }
}