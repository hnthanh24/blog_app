package com.vti.blogapp.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = PostIdExistsValidator.class)
@Target(PARAMETER)
@Retention(RUNTIME)
public @interface PostIdExists {
    String message() default "{post.id.Exists.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
