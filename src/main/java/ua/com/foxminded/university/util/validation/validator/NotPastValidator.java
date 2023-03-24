package ua.com.foxminded.university.util.validation.validator;

import ua.com.foxminded.university.consumer.dto.LessonDTO;
import ua.com.foxminded.university.util.validation.NotPast;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.ZonedDateTime;

public class NotPastValidator implements ConstraintValidator<NotPast, LessonDTO> {

    @Override
    public boolean isValid(LessonDTO value, ConstraintValidatorContext context) {
        if (value.getStartTime() == null) {
            return false;
        }
        return value.getStartTime().isAfter(ZonedDateTime.now());
    }
}
