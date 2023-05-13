package ua.com.foxminded.university.persistance.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import ua.com.foxminded.university.persistance.model.StudentEntity;

import java.util.Optional;

class StudentRepositoryTest extends RepositoryTestBase {


    @Autowired
    StudentRepository repository;

    @Test
    void save_returnSavedStudent_ifPersist() {
        StudentEntity expectedStudent = new StudentEntity.Builder("testStudent", "testST").build();
        StudentEntity actualStudent = repository.save(expectedStudent);
        Assertions.assertEquals(expectedStudent, actualStudent);
    }

    @Test
    void save_thrownInvalidDataAccessApiUsageException_ifInputNull() {
        StudentEntity inputStudent = null;
        Assertions.assertThrows(InvalidDataAccessApiUsageException.class,
                () -> repository.save(inputStudent));

    }

    @Test
    void findById_returnStudent_ifExists() {
        StudentEntity inputStudent = new StudentEntity.Builder("testStudent", "testSt").build();
        StudentEntity expectedStudent = repository.saveAndFlush(inputStudent);
        StudentEntity actualStudent = repository.findById(repository.count()+1).get();
        Assertions.assertEquals(expectedStudent, actualStudent);
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
        Optional<StudentEntity> optional = repository.findById(inputId);
        Assertions.assertTrue(optional.isEmpty());
    }


    @Test
    void update_returnUpdated_IfExists() {
        StudentEntity expectedStudent = repository.findById(1L).get();
        expectedStudent.setFirstName("newName");
        StudentEntity actualStudent = repository.saveAndFlush(expectedStudent);
        Assertions.assertEquals(expectedStudent, actualStudent);
    }

    @Test
    void update_thrownInvalidDataAccessApiUsageException_IfInputNull() {
        StudentEntity inputStudent = null;
        Assertions.assertThrows(InvalidDataAccessApiUsageException.class,
                () -> repository.saveAndFlush(inputStudent));
    }

    @Test
    void update_createAndReturnNew_IfInputEntityWithoutId() {
        StudentEntity expectedStudent = repository.findById(1L).get();
        long beforeCount = repository.count();
        StudentEntity inputStudent = new StudentEntity.Builder(expectedStudent.getFirstName(), expectedStudent.getLastName()).setAge((byte) 54).build();
        repository.saveAndFlush(inputStudent);
        long actualCount = repository.count();
        Assertions.assertNotEquals(beforeCount, actualCount);
    }

    @Test
    void delete_deleted_ifExists() {
        repository.deleteById(1L);
        Optional<StudentEntity> actual = repository.findById(1L);
        Assertions.assertTrue(actual.isEmpty());
    }


    @Test
    void delete_thrownInvalidDataAccessApiUsageException_ifInputNull() {
        Assertions.assertThrows(InvalidDataAccessApiUsageException.class,
                () -> repository.deleteById(null));
    }
}
