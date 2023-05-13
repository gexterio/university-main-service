package ua.com.foxminded.university.util.validation.validator;

import ua.com.foxminded.university.util.validation.NotPast;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.ZonedDateTime;

public class NotPastValidator implements ConstraintValidator<NotPast, ZonedDateTime> {

    @Override
    public boolean isValid(ZonedDateTime value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return value.isAfter(ZonedDateTime.now());
    }
}
