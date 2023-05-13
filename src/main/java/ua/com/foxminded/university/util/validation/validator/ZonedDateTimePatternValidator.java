package ua.com.foxminded.university.util.validation.validator;

import ua.com.foxminded.university.util.validation.ZonedDateTimePattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ZonedDateTimePatternValidator implements ConstraintValidator<ZonedDateTimePattern, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            ZonedDateTime.parse(value, DateTimeFormatter.ISO_ZONED_DATE_TIME);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
