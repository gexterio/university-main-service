package ua.com.foxminded.university.util.validation;



import ua.com.foxminded.university.util.validation.validator.AdultTeacherValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AdultTeacherValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AdultTeacher {

    String message() default "Teacher should be older than 21";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
