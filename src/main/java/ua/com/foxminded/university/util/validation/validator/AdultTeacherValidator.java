package ua.com.foxminded.university.util.validation.validator;

import ua.com.foxminded.university.util.validation.AdultTeacher;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AdultTeacherValidator implements ConstraintValidator<AdultTeacher, Byte> {

    @Override
    public boolean isValid(Byte value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return value >= 21;
    }
}
