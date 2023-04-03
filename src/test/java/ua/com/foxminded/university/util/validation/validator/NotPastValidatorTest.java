package ua.com.foxminded.university.util.validation.validator;

import org.junit.jupiter.api.Test;
import ua.com.foxminded.university.consumer.dto.LessonDTO;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.ZonedDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NotPastValidatorTest {

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private LessonDTO lesson = new LessonDTO.Builder().setId(1L).setName("LessonName").setDuration(60).setStartTime(ZonedDateTime.now().plusDays(10)).build();

    @Test
    void isValid_thrownViolation_lessonStartTimeIsNull() {
        lesson.setStartTime(null);
        int expectedSize = 1;
        String message = "Lesson can't start in the past =)";
        Set<ConstraintViolation<LessonDTO>> violations = validator.validate(lesson);
        ConstraintViolation<LessonDTO> violation = violations.stream().findFirst().get();
        assertEquals(expectedSize, violations.size());
        assertEquals(message, violation.getMessage());
        assertEquals(lesson.getStartTime(), violation.getInvalidValue());
    }

    @Test
    void isValid_thrownViolation_lessonStartTimeInPast() {
        lesson.setStartTime(ZonedDateTime.now().minusDays(1));
        int expectedSize = 1;
        String message = "Lesson can't start in the past =)";
        Set<ConstraintViolation<LessonDTO>> violations = validator.validate(lesson);
        ConstraintViolation<LessonDTO> violation = violations.stream().findFirst().get();
        assertEquals(expectedSize, violations.size());
        assertEquals(message, violation.getMessage());
        assertEquals(lesson.getStartTime(), violation.getInvalidValue());
    }

    @Test
    void isValid_doNothing_lessonStartTimeInFuture() {
        lesson.setStartTime(ZonedDateTime.now().plusDays(1));
        Set<ConstraintViolation<LessonDTO>> violations = validator.validate(lesson);
        assertTrue(violations.isEmpty());
    }
}