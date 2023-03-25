package ua.com.foxminded.university.util.validation.validator;

import org.junit.jupiter.api.Test;
import ua.com.foxminded.university.consumer.dto.TeacherDTO;
import ua.com.foxminded.university.util.validation.AdultTeacher;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AdultTeacherValidatorTest {

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private TeacherDTO teacher = new TeacherDTO.Builder().setId(1L).setFirstName("Name").setLastName("LastName").setAge((byte) 20).setGrade("someGrade").setExperience(20).setEmail("example@gmail.com").build();

    @Test
    void isValid_thrownViolation_teacherAgeIsNull() {
        teacher.setAge(null);
        int expectedSize = 1;
        Optional<Method> optional = Arrays.stream(AdultTeacher.class.getMethods()).filter(m -> m.getName().equals("message")).findFirst();
        assertTrue(optional.isPresent());
        String message = optional.get().getDefaultValue().toString();
        Set<ConstraintViolation<TeacherDTO>> violations = validator.validate(teacher);
        ConstraintViolation<TeacherDTO> violation = violations.stream().findFirst().get();
        assertEquals(expectedSize, violations.size());
        assertEquals(message, violation.getMessage());
        assertEquals(teacher.getAge(), violation.getInvalidValue());
    }

    @Test
    void isValid_thrownViolation_teacherAgeIsLessThan21() {
        teacher.setAge((byte) 17);
        int expectedSize = 1;
        Optional<Method> optional = Arrays.stream(AdultTeacher.class.getMethods()).filter(m -> m.getName().equals("message")).findFirst();
        assertTrue(optional.isPresent());
        String message = optional.get().getDefaultValue().toString();
        Set<ConstraintViolation<TeacherDTO>> violations = validator.validate(teacher);
        ConstraintViolation<TeacherDTO> violation = violations.stream().findFirst().get();
        assertEquals(expectedSize, violations.size());
        assertEquals(message, violation.getMessage());
        assertEquals(teacher.getAge(), violation.getInvalidValue());
    }

    @Test
    void isValid_doNothing_teacherAgeIsEqualsThan21() {
        teacher.setAge((byte) 21);
        Set<ConstraintViolation<TeacherDTO>> violations = validator.validate(teacher);
        assertTrue(violations.isEmpty());
    }

    @Test
    void isValid_doNothing_teacherAgeIsMoreThan21() {
        teacher.setAge((byte) 40);
        Set<ConstraintViolation<TeacherDTO>> violations = validator.validate(teacher);
        assertTrue(violations.isEmpty());
    }
}