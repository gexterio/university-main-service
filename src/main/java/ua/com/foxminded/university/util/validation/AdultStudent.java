package ua.com.foxminded.university.util.validation;

import ua.com.foxminded.university.util.validation.validator.AdultStudentValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AdultStudentValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AdultStudent {

    String message() default "Student should be older than 18";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
