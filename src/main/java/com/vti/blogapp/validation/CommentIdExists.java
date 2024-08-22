package com.vti.blogapp.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = CommentIdExistsValidator.class)
@Target(PARAMETER)
@Retention(RUNTIME)
public @interface CommentIdExists {
    String message() default "The comment does not exist.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
