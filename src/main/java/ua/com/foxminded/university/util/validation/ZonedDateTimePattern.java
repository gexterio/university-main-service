package ua.com.foxminded.university.util.validation;

import ua.com.foxminded.university.util.validation.validator.ZonedDateTimePatternValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ZonedDateTimePatternValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ZonedDateTimePattern {

    String message() default "Parameter is not matches with pattern! It should look like: '2011-12-03T10:15:30+01:00[Europe/Paris]'";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
