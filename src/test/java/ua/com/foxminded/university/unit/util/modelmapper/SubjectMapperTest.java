package ua.com.foxminded.university.unit.util.modelmapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.foxminded.university.consumer.dto.SubjectDTO;
import ua.com.foxminded.university.annotation.UnitTest;
import ua.com.foxminded.university.persistance.model.SubjectEntity;
import ua.com.foxminded.university.util.modelmapper.SubjectMapper;

@UnitTest
class SubjectMapperTest {

    @Autowired
    SubjectMapper subjectMapper;

    @Test
    void toEntity_returnEntity_inputNotNull() {
        SubjectDTO dto = new SubjectDTO.Builder()
                .setId(1L)
                .setName("subject")
                .setDescription("description")
                .build();
        SubjectEntity entity = subjectMapper.toEntity(dto);
        Assertions.assertEquals(dto.getId(), entity.getId());
        Assertions.assertEquals(dto.getName(), entity.getName());
        Assertions.assertEquals(dto.getDescription(), entity.getDescription());
    }


    @Test
    void toDto_returnDto_inputNotNull() {
        SubjectEntity entity = new SubjectEntity.Builder("subject")
                .setId(1L)
                .setDescription("description")
                .build();
        SubjectDTO dto = subjectMapper.toDto(entity);
        Assertions.assertEquals(entity.getId(), dto.getId());
        Assertions.assertEquals(entity.getName(), dto.getName());
        Assertions.assertEquals(entity.getDescription(), dto.getDescription());
    }
}