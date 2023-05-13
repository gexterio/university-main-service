package ua.com.foxminded.university.util.validation.validator;

import ua.com.foxminded.university.util.validation.GroupNamePattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GroupNamePatternValidator implements ConstraintValidator<GroupNamePattern, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return value.matches("[A-Z]{2}-\\d{2}");
    }
}
