package ua.com.foxminded.university.util.validation.validator;

import org.junit.jupiter.api.Test;
import ua.com.foxminded.university.consumer.dto.GroupDTO;
import ua.com.foxminded.university.util.validation.GroupNamePattern;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GroupNamePatternValidatorTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private GroupDTO group = new GroupDTO.Builder().setId(1L).setName("AA-11").build();

    @Test
    void isValid_thrownViolation_groupNameIsNull() {
        group.setName(null);
        int expectedSize = 2;
        Optional<Method> optional = Arrays.stream(GroupNamePattern.class.getMethods()).filter(m -> m.getName().equals("message")).findFirst();
        assertTrue(optional.isPresent());
        String message = optional.get().getDefaultValue().toString();
        Set<ConstraintViolation<GroupDTO>> violations = validator.validate(group);
        ConstraintViolation<GroupDTO> violation = violations.stream().filter(v -> v.getMessage().startsWith("Group name should be match")).findFirst().get();
        assertEquals(expectedSize, violations.size());
        assertEquals(message, violation.getMessage());
        assertEquals(group.getName(), violation.getInvalidValue());
    }

    @Test
    void isValid_thrownViolation_groupNameNotMatchesThePattern() {
        group.setName("InvalidName");
        int expectedSize = 1;
        Optional<Method> optional = Arrays.stream(GroupNamePattern.class.getMethods()).filter(m -> m.getName().equals("message")).findFirst();
        assertTrue(optional.isPresent());
        String message = optional.get().getDefaultValue().toString();
        Set<ConstraintViolation<GroupDTO>> violations = validator.validate(group);
        ConstraintViolation<GroupDTO> violation = violations.stream().findFirst().get();
        assertEquals(expectedSize, violations.size());
        assertEquals(message, violation.getMessage());
        assertEquals(group.getName(), violation.getInvalidValue());
    }

    @Test
    void isValid_doNothing_groupNameMatchesThePattern() {
        group.setName("AB-12");
        Set<ConstraintViolation<GroupDTO>> violations = validator.validate(group);
        assertTrue(violations.isEmpty());
    }
}