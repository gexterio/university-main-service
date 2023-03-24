package ua.com.foxminded.university.util.validation;


import ua.com.foxminded.university.util.validation.validator.GroupNamePatternValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = GroupNamePatternValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GroupNamePattern {

    String message() default "Group name should be match the patter: [A-Z]{2}-\\d{2}, like AA-11";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
