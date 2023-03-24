package ua.com.foxminded.university.util.validation.validator;

import ua.com.foxminded.university.util.validation.AdultStudent;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class AdultStudentValidator implements ConstraintValidator<AdultStudent, Byte> {

    @Override
    public boolean isValid(Byte value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return value >= 18;
    }
}
