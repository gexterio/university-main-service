package ua.com.foxminded.university.util.validation;

import ua.com.foxminded.university.util.validation.validator.TimeRangeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Constraint(validatedBy = TimeRangeValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface TimeRange {

    String message() default "Parameter is invalid! Valid Parameters are: day, month";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
