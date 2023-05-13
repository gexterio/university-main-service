package ua.com.foxminded.university.util.validation;

import ua.com.foxminded.university.util.validation.validator.NotPastValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NotPastValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotPast {

    String message() default "Lesson can't start in the past =)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
