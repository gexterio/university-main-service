package ua.com.foxminded.university.util.validation.validator;

import ua.com.foxminded.university.consumer.dto.GroupDTO;
import ua.com.foxminded.university.util.validation.GroupNamePattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GroupNamePatternValidator implements ConstraintValidator<GroupNamePattern, GroupDTO> {

    @Override
    public boolean isValid(GroupDTO value, ConstraintValidatorContext context) {
        if (value.getName() == null) {
            return false;
        }
        return value.getName().matches("[A-Z]{2}-\\d{2}");
    }
}
