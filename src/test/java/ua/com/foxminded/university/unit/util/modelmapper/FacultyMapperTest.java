package ua.com.foxminded.university.unit.util.modelmapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.foxminded.university.consumer.dto.FacultyDTO;
import ua.com.foxminded.university.unit.persistance.annotation.UnitTest;
import ua.com.foxminded.university.persistance.model.FacultyEntity;
import ua.com.foxminded.university.util.modelmapper.FacultyMapper;

@UnitTest
class FacultyMapperTest {

    @Autowired
    FacultyMapper facultyMapper;

    @Test
    void toEntity_returnEntity_inputNotNull() {
        FacultyDTO dto = new FacultyDTO.Builder()
                .setId(1L)
                .setName("testName")
                .setDuration(60)
                .build();
        FacultyEntity entity = facultyMapper.toEntity(dto);

        Assertions.assertEquals(dto.getId(), entity.getId());
        Assertions.assertEquals(dto.getName(), entity.getName());
        Assertions.assertEquals(dto.getDuration(), entity.getDuration());
    }

    @Test
    void toDto_returnDto_inputNotNull() {
        FacultyEntity entity = new FacultyEntity.Builder("testName")
                .setId(1L)
                .setDuration(60)
                .build();
        FacultyDTO dto = facultyMapper.toDto(entity);
        Assertions.assertEquals(entity.getId(), dto.getId());
        Assertions.assertEquals(entity.getName(), dto.getName());
        Assertions.assertEquals(entity.getDuration(), dto.getDuration());
    }

}