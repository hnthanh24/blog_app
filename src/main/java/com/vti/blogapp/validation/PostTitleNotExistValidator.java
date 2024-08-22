package com.vti.blogapp.validation;

import com.vti.blogapp.repository.PostRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PostTitleNotExistValidator implements ConstraintValidator<PostTitleNotExist, String> {
    private final PostRepository postRepository;

    @Override
    public boolean isValid(String title, ConstraintValidatorContext context) {
        return !postRepository.existsByTitle(title);
    }
}
