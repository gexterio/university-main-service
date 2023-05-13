package ua.com.foxminded.university.util.validation.validator;

import ua.com.foxminded.university.util.validation.TimeRange;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TimeRangeValidator implements ConstraintValidator<TimeRange, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return "day".equals(value) || "month".equals(value);
    }
}
