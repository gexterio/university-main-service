package ua.com.foxminded.university.unit.persistance.repository;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import ua.com.foxminded.university.persistance.model.LessonEntity;
import ua.com.foxminded.university.persistance.repository.LessonRepository;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


class LessonRepositoryTest extends RepositoryTestBase {

    @Autowired
    private LessonRepository repository;

    private final Long student_id = 1L;

    private final Long teacher_id = 1L;

    private final Long lesson_id = 12L;

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
    void findAllLessonsByStudentIdAndDate_returnEmptyList_ifNotExists() {
        ZonedDateTime from = ZonedDateTime.of(2000, 3, 13, 0, 0, 0, 0, ZONE);
        ZonedDateTime to = ZonedDateTime.of(2000, 3, 14, 0, 0, 0, 0, ZONE);
        List<LessonEntity> actualLessons = repository.findAllLessonsByStudentIdAndDate(student_id, from, to);
        Assertions.assertTrue(actualLessons.isEmpty());
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
    void findAllLessonsByTeacherIdAndDate_returnEmptyList_ifNotExists() {

        ZonedDateTime from = ZonedDateTime.of(2000, 3, 13, 0, 0, 0, 0, ZONE);
        ZonedDateTime to = ZonedDateTime.of(2000, 3, 14, 0, 0, 0, 0, ZONE);
        List<LessonEntity> actualLessons = repository.findAllLessonsByTeacherIdAndDate(student_id, from, to);
        Assertions.assertTrue(actualLessons.isEmpty());
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

    @Test
    void save_returnSavedLesson_ifPersist() {
        LessonEntity expectedLesson = new LessonEntity.Builder("testLesson").build();
        LessonEntity actualLesson = repository.save(expectedLesson);
        Assertions.assertEquals(expectedLesson, actualLesson);
    }

    @Test
    void save_thrownInvalidDataAccessApiUsageException_ifInputNull() {
        LessonEntity inputLesson = null;
        Assertions.assertThrows(InvalidDataAccessApiUsageException.class,
                () -> repository.save(inputLesson));

    }

    @Test
    void findById_returnLesson_ifExists() {
        LessonEntity inputLesson = new LessonEntity.Builder("testLesson").build();
        LessonEntity expectedLesson = repository.saveAndFlush(inputLesson);
        LessonEntity actualLesson = repository.findById(lesson_id).get();
        Assertions.assertEquals(expectedLesson, actualLesson);
    }

    @Test
    void findById_thrownInvalidDataAccessApiUsageException_ifInputNull() {
        Long inputId = null;
        Assertions.assertThrows(InvalidDataAccessApiUsageException.class,
                () -> repository.findById(inputId));
    }

    @Test
    void findById_returnEmptyOptional_ifNotExists() {
        long inputId = -1L;
        Optional<LessonEntity> optional = repository.findById(inputId);
        Assertions.assertTrue(optional.isEmpty());
    }


    @Test
    void update_returnUpdated_IfExists() {
        LessonEntity expectedLesson = repository.findById(1L).get();
        expectedLesson.setName("newName");
        LessonEntity actualLesson = repository.saveAndFlush(expectedLesson);
        Assertions.assertEquals(expectedLesson, actualLesson);
    }

    @Test
    void update_thrownInvalidDataAccessApiUsageException_IfInputNull() {
        LessonEntity inputlesson = null;
        Assertions.assertThrows(InvalidDataAccessApiUsageException.class,
                () -> repository.saveAndFlush(inputlesson));
    }

    @Test
    void update_createAndReturnNew_IfInputEntityWithoutId() {
        LessonEntity expectedLesson = repository.findById(1L).get();
        long beforeCount = repository.count();
        LessonEntity inputLesson = new LessonEntity.Builder(expectedLesson.getName()).setDuration(20).build();
        repository.saveAndFlush(inputLesson);
        long actualCount = repository.count();
        Assertions.assertNotEquals(beforeCount, actualCount);
    }

    @Test
    void delete_deleted_ifExists() {
        repository.deleteById(1L);
        Optional<LessonEntity> actual = repository.findById(1L);
        Assertions.assertTrue(actual.isEmpty());
    }


    @Test
    void delete_thrownInvalidDataAccessApiUsageException_ifInputNull() {
        Assertions.assertThrows(InvalidDataAccessApiUsageException.class,
                () -> repository.deleteById(null));
    }
}
