package ua.com.foxminded.university.integration.persistance.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.foxminded.university.persistance.model.LessonEntity;
import ua.com.foxminded.university.persistance.repository.TeacherRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

class TeacherRepositoryTest extends RepositoryIntegrationTestBase {

    @Autowired
    TeacherRepository repository;

    @Test
    void findAllLessonsForDayByStudentAndDate_returnLessonsList_ifExists() {
        int expectedSize = 1;
        String expectedTime = "2023-02-12T22:33:02.727123+04:00[Asia/Tbilisi]";
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate localDate = LocalDate.of(2023, 2, 12);
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        List<LessonEntity> actualLessons = repository.findAllLessonsForDayByTeacherIdAndDate(1L, date);
        String actualTime = actualLessons.get(0).getStartTime().toString();
        Assertions.assertEquals(expectedSize, actualLessons.size());
        Assertions.assertEquals(expectedTime, actualTime);
    }

    @Test
    void findAllLessonsForMonthByStudentIdAndDate_returnLessonsList_ifExists() {
        int expectedSize = 2;
        String expectedTime = "2023-02-12T22:33:02.727123+04:00[Asia/Tbilisi]";
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate localDate = LocalDate.of(2023, 2, 1);
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        List<LessonEntity> lessons = repository.findAllLessonsForMonthByTeacherIdAndDate(1L, date);
        String actualTime = lessons.get(0).getStartTime().toString();
        Assertions.assertEquals(expectedSize, lessons.size());
        Assertions.assertEquals(expectedTime, actualTime);
    }

}
