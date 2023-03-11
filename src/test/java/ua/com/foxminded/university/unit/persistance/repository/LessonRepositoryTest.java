package ua.com.foxminded.university.unit.persistance.repository;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.foxminded.university.persistance.model.LessonEntity;
import ua.com.foxminded.university.persistance.repository.LessonRepository;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

class LessonRepositoryTest extends RepositoryTestBase {

    @Autowired
    private LessonRepository repository;


    private final Long student_id = 1L;

    private final Long teacher_id = 1L;

    public static final ZoneId ZONE = ZoneId.of("Asia/Tbilisi");


    @Test
    void findAllLessonsByStudentIdAndDate_returnListForDay_ifExists() {
        int expectedSize = 1;
        List<LessonEntity> expectedLessons = new ArrayList<>();
        expectedLessons.add(repository.findById(5L).get());
        ZonedDateTime from = ZonedDateTime.of(2023, 3, 13, 0, 0, 0, 0, ZONE);
        ZonedDateTime to = ZonedDateTime.of(2023, 3, 14, 0, 0, 0, 0, ZONE);
        List<LessonEntity> actualLessons = repository.findAllLessonsByStudentIdAndDate(student_id, from, to);
        Assertions.assertEquals(expectedSize, actualLessons.size());
        Assertions.assertEquals(expectedLessons, actualLessons);
    }

    @Test
    void findAllLessonsByStudentIdAndDate_returnListForMonth_ifExists() {
        int expectedSize = 3;
        List<LessonEntity> expectedLessons = new ArrayList<>();
        for (long i = 1; i <= expectedSize; i++) {
            expectedLessons.add(repository.findById(i).get());
        }
        ZonedDateTime from = ZonedDateTime.of(2023, 2, 1, 0, 0, 0, 0, ZoneId.of("Asia/Tbilisi"));
        ZonedDateTime to = ZonedDateTime.of(2023, 3, 1, 0, 0, 0, 0, ZoneId.of("Asia/Tbilisi"));
        List<LessonEntity> actualLessons = repository.findAllLessonsByStudentIdAndDate(student_id, from, to);
        Assertions.assertEquals(expectedSize, actualLessons.size());
        Assertions.assertEquals(expectedLessons, actualLessons);

    }

    @Test
    void findAllLessonsByTeacherIdAndDate_returnListForDay_ifExists() {
        int expectedSize = 1;
        List<LessonEntity> expectedLessons = new ArrayList<>();
        expectedLessons.add(repository.findById(1L).get());
        ZonedDateTime from = ZonedDateTime.of(2023, 2, 22, 0, 0, 0, 0, ZONE);
        ZonedDateTime to = ZonedDateTime.of(2023, 2, 23, 0, 0, 0, 0, ZONE);
        List<LessonEntity> actualLessons = repository.findAllLessonsByTeacherIdAndDate(teacher_id, from, to);
        Assertions.assertEquals(expectedSize, actualLessons.size());
        Assertions.assertEquals(expectedLessons, actualLessons);
    }

    @Test
    void findAllLessonsByTeacherIdAndDate_returnListForMonth_ifExists() {
        int expectedSize = 3;
        List<LessonEntity> expectedLessons = new ArrayList<>();
        for (long i = 1; i <= expectedSize; i++) {
            expectedLessons.add(repository.findById(i).get());
        }
        ZonedDateTime from = ZonedDateTime.of(2023, 2, 1, 0, 0, 0, 0, ZoneId.of("Asia/Tbilisi"));
        ZonedDateTime to = ZonedDateTime.of(2023, 3, 1, 0, 0, 0, 0, ZoneId.of("Asia/Tbilisi"));
        List<LessonEntity> actualLessons = repository.findAllLessonsByTeacherIdAndDate(teacher_id, from, to);
        Assertions.assertEquals(expectedSize, actualLessons.size());
        Assertions.assertEquals(expectedLessons, actualLessons);
    }
}
