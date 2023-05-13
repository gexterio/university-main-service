package ua.com.foxminded.university.unit.util.modelmapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.foxminded.university.annotation.MappersTest;
import ua.com.foxminded.university.consumer.dto.FacultyDTO;
import ua.com.foxminded.university.consumer.dto.GroupDTO;
import ua.com.foxminded.university.persistance.model.FacultyEntity;
import ua.com.foxminded.university.persistance.model.GroupEntity;
import ua.com.foxminded.university.util.modelmapper.GroupMapper;

@MappersTest
class GroupMapperTest {

    @Autowired
    GroupMapper groupMapper;

    @Test
    void toEntity_returnEntity_inputNotNull() {
        GroupDTO dto = new GroupDTO.Builder()
                .setId(1L)
                .setName("testName")
                .setFaculty(new FacultyDTO.Builder().setName("faculty").build())
                .build();
        GroupEntity entity = groupMapper.toEntity(dto);
        Assertions.assertEquals(dto.getId(), entity.getId());
        Assertions.assertEquals(dto.getName(), entity.getName());
        Assertions.assertEquals(dto.getFaculty().getName(), entity.getFaculty().getName());
    }

    @Test
    void toDto_returnDto_inputNotNull() {
        GroupEntity entity = new GroupEntity.Builder("testName")
                .setId(1L)
                .setFaculty(new FacultyEntity.Builder("faculty").build())
                .build();
        GroupDTO dto = groupMapper.toDto(entity);
        Assertions.assertEquals(entity.getId(), dto.getId());
        Assertions.assertEquals(entity.getName(), dto.getName());
        Assertions.assertEquals(entity.getFaculty().getName(), dto.getFaculty().getName());
    }
}