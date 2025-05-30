package sdu.project.chooserback.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TaskLevelRequiredValidator.class)
public @interface TaskLevelRequired {
    String message() default "Task level is required when mode is TASK";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
} 