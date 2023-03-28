package ua.com.foxminded.university.util.validation.validator;

import org.junit.jupiter.api.Test;
import ua.com.foxminded.university.consumer.dto.StudentDTO;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AdultStudentValidatorTest {

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private StudentDTO student = new StudentDTO.Builder().setId(1L).setFirstName("Name").setLastName("LastName").setAge((byte) 20).setCourse((byte) 2).setEmail("example@gmail.com").build();

    @Test
    void isValid_thrownViolation_studentAgeIsNull() {
        student.setAge(null);
        int expectedSize = 1;
        String message = "Student should be older than 18";
        Set<ConstraintViolation<StudentDTO>> violations = validator.validate(student);
        ConstraintViolation<StudentDTO> violation = violations.stream().findFirst().get();
        assertEquals(expectedSize, violations.size());
        assertEquals(message, violation.getMessage());
        assertEquals(student.getAge(), violation.getInvalidValue());
    }

    @Test
    void isValid_thrownViolation_studentAgeIsLessThan18() {
        student.setAge((byte) 17);
        int expectedSize = 1;

        String message = "Student should be older than 18";
        Set<ConstraintViolation<StudentDTO>> violations = validator.validate(student);
        ConstraintViolation<StudentDTO> violation = violations.stream().findFirst().get();
        assertEquals(expectedSize, violations.size());
        assertEquals(message, violation.getMessage());
        assertEquals(student.getAge(), violation.getInvalidValue());
    }

    @Test
    void isValid_doNothing_studentAgeIsEqualsThan18() {
        student.setAge((byte) 18);
        Set<ConstraintViolation<StudentDTO>> violations = validator.validate(student);
        assertTrue(violations.isEmpty());
    }

    @Test
    void isValid_doNothing_studentAgeIsMoreThan18() {
        student.setAge((byte) 20);
        Set<ConstraintViolation<StudentDTO>> violations = validator.validate(student);
        assertTrue(violations.isEmpty());
    }


}