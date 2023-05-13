package ua.com.foxminded.university.unit.util.modelmapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.foxminded.university.annotation.MappersTest;
import ua.com.foxminded.university.consumer.dto.GroupDTO;
import ua.com.foxminded.university.consumer.dto.StudentDTO;
import ua.com.foxminded.university.persistance.model.GroupEntity;
import ua.com.foxminded.university.persistance.model.StudentEntity;
import ua.com.foxminded.university.util.modelmapper.StudentMapper;

@MappersTest
class StudentMapperTest {

    @Autowired
    StudentMapper studentMapper;

    @Test
    void toEntity_returnEntity_inputNotNull() {
        StudentDTO dto = new StudentDTO.Builder()
                .setId(1L)
                .setFirstName("firstName")
                .setLastName("lastName")
                .setAge((byte) 24)
                .setEmail("email")
                .setCourse((byte) 3)
                .setGroup(new GroupDTO.Builder().setName("group").build())
                .build();
        StudentEntity entity = studentMapper.toEntity(dto);
        Assertions.assertEquals(dto.getId(), entity.getId());
        Assertions.assertEquals(dto.getFirstName(), entity.getFirstName());
        Assertions.assertEquals(dto.getLastName(), entity.getLastName());
        Assertions.assertEquals(dto.getAge(), entity.getAge());
        Assertions.assertEquals(dto.getEmail(), entity.getEmail());
        Assertions.assertEquals(dto.getCourse(), entity.getCourse());
        Assertions.assertEquals(dto.getGroup().getName(), entity.getGroup().getName());
    }


    @Test
    void toDto_returnDto_inputNotNull() {
        StudentEntity entity = new StudentEntity.Builder("firstName", "lastName")
                .setId(1L)
                .setAge((byte) 24)
                .setEmail("email")
                .setCourse((byte) 3)
                .setGroup(new GroupEntity.Builder("group").build())
                .build();
        StudentDTO dto = studentMapper.toDto(entity);
        Assertions.assertEquals(entity.getId(), dto.getId());
        Assertions.assertEquals(entity.getFirstName(), dto.getFirstName());
        Assertions.assertEquals(entity.getLastName(), dto.getLastName());
        Assertions.assertEquals(entity.getAge(), dto.getAge());
        Assertions.assertEquals(entity.getEmail(), dto.getEmail());
        Assertions.assertEquals(entity.getCourse(), dto.getCourse());
        Assertions.assertEquals(entity.getGroup().getName(), dto.getGroup().getName());
    }
}