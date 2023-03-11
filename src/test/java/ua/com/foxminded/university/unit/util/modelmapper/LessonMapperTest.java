package ua.com.foxminded.university.unit.util.modelmapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.foxminded.university.consumer.dto.GroupDTO;
import ua.com.foxminded.university.consumer.dto.LessonDTO;
import ua.com.foxminded.university.consumer.dto.SubjectDTO;
import ua.com.foxminded.university.consumer.dto.TeacherDTO;
import ua.com.foxminded.university.annotation.UnitTest;
import ua.com.foxminded.university.persistance.model.GroupEntity;
import ua.com.foxminded.university.persistance.model.LessonEntity;
import ua.com.foxminded.university.persistance.model.SubjectEntity;
import ua.com.foxminded.university.persistance.model.TeacherEntity;
import ua.com.foxminded.university.util.modelmapper.LessonMapper;

import java.time.ZonedDateTime;

@UnitTest
class LessonMapperTest {

    @Autowired
    LessonMapper lessonMapper;

    @Test
    void toEntity_returnEntity_inputNotNull() {
        LessonDTO dto = new LessonDTO.Builder()
                .setId(1L)
                .setName("testName")
                .setStartTime(ZonedDateTime.now())
                .setDuration(60)
                .setSubject(new SubjectDTO.Builder().setName("subject").build())
                .setGroup(new GroupDTO.Builder().setName("group").build())
                .setTeacher(new TeacherDTO.Builder().setFirstName("firstName").build())
                .build();
        LessonEntity entity = lessonMapper.toEntity(dto);
        Assertions.assertEquals(dto.getId(), entity.getId());
        Assertions.assertEquals(dto.getName(), entity.getName());
        Assertions.assertEquals(dto.getStartTime(), entity.getStartTime());
        Assertions.assertEquals(dto.getDuration(), entity.getDuration());
        Assertions.assertEquals(dto.getSubject().getName(), entity.getSubject().getName());
        Assertions.assertEquals(dto.getGroup().getName(), entity.getGroup().getName());
        Assertions.assertEquals(dto.getTeacher().getFirstName(), entity.getTeacher().getFirstName());
    }


    @Test
    void toDto_returnDto_inputNotNull() {
        LessonEntity entity = new LessonEntity.Builder("testName")
                .setId(1L)
                .setStartTime(ZonedDateTime.now())
                .setDuration(60)
                .setSubject(new SubjectEntity.Builder("subject").build())
                .setGroup(new GroupEntity.Builder("group").build())
                .setTeacher(new TeacherEntity.Builder("firstName", "lastName").build())
                .build();
        LessonDTO dto = lessonMapper.toDto(entity);
        Assertions.assertEquals(entity.getId(), dto.getId());
        Assertions.assertEquals(entity.getName(), dto.getName());
        Assertions.assertEquals(entity.getStartTime(), dto.getStartTime());
        Assertions.assertEquals(entity.getDuration(), dto.getDuration());
        Assertions.assertEquals(entity.getSubject().getName(), dto.getSubject().getName());
        Assertions.assertEquals(entity.getGroup().getName(), dto.getGroup().getName());
        Assertions.assertEquals(entity.getTeacher().getFirstName(), dto.getTeacher().getFirstName());
    }

}