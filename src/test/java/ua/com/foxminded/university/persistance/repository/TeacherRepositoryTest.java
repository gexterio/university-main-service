package ua.com.foxminded.university.persistance.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import ua.com.foxminded.university.persistance.model.TeacherEntity;

import java.util.Optional;

class TeacherRepositoryTest extends RepositoryTestBase {

    @Autowired
    TeacherRepository repository;


    @Test
    void save_returnSavedTeacher_ifPersist() {
        TeacherEntity expectedTeacher = new TeacherEntity.Builder("testTeacher", "testST").build();
        TeacherEntity actualTeacher = repository.save(expectedTeacher);
        Assertions.assertEquals(expectedTeacher, actualTeacher);
    }

    @Test
    void save_thrownInvalidDataAccessApiUsageException_ifInputNull() {
        TeacherEntity inputTeacher = null;
        Assertions.assertThrows(InvalidDataAccessApiUsageException.class,
                () -> repository.save(inputTeacher));

    }

    @Test
    void findById_returnTeacher_ifExists() {
        TeacherEntity inputTeacher = new TeacherEntity.Builder("testTeacher", "testSt").build();
        TeacherEntity expectedTeacher = repository.saveAndFlush(inputTeacher);
        TeacherEntity actualTeacher = repository.findById(repository.count()+1).get();
        Assertions.assertEquals(expectedTeacher, actualTeacher);
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
        Optional<TeacherEntity> optional = repository.findById(inputId);
        Assertions.assertTrue(optional.isEmpty());
    }


    @Test
    void update_returnUpdated_IfExists() {
        TeacherEntity expectedTeacher = repository.findById(1L).get();
        expectedTeacher.setFirstName("newName");
        TeacherEntity actualTeacher = repository.saveAndFlush(expectedTeacher);
        Assertions.assertEquals(expectedTeacher, actualTeacher);
    }

    @Test
    void update_thrownInvalidDataAccessApiUsageException_IfInputNull() {
        TeacherEntity inputTeacher = null;
        Assertions.assertThrows(InvalidDataAccessApiUsageException.class,
                () -> repository.saveAndFlush(inputTeacher));
    }

    @Test
    void update_createAndReturnNew_IfInputEntityWithoutId() {
        TeacherEntity expectedTeacher = repository.findById(1L).get();
        long beforeCount = repository.count();
        TeacherEntity inputTeacher = new TeacherEntity.Builder(expectedTeacher.getFirstName(), expectedTeacher.getLastName()).setAge((byte) 54).build();
        repository.saveAndFlush(inputTeacher);
        long actualCount = repository.count();
        Assertions.assertNotEquals(beforeCount, actualCount);
    }

    @Test
    void delete_deleted_ifExists() {
        repository.deleteById(1L);
        Optional<TeacherEntity> actual = repository.findById(1L);
        Assertions.assertTrue(actual.isEmpty());
    }


    @Test
    void delete_thrownInvalidDataAccessApiUsageException_ifInputNull() {
        Assertions.assertThrows(InvalidDataAccessApiUsageException.class,
                () -> repository.deleteById(null));
    }
}


