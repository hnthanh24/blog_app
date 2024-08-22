package com.vti.blogapp.controller;

import com.vti.blogapp.dto.PostDto;
import com.vti.blogapp.form.PostCreateForm;
import com.vti.blogapp.form.PostFilterForm;
import com.vti.blogapp.form.PostUpdateForm;
import com.vti.blogapp.service.PostService;
import com.vti.blogapp.validation.PostIdExists;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@AllArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/api/v1/posts")
    public Page<PostDto> findAll(PostFilterForm form, Pageable pageable) {
        return postService.findAll(form, pageable);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostDto findById(@PathVariable("id") @PostIdExists Long id) {
        return postService.findById(id);
    }

    @PostMapping("/api/v1/posts")
    public PostDto create(@RequestBody @Valid PostCreateForm form) {
        return postService.create(form);
    }

    @PutMapping("/api/v1/posts/{id}")
    public PostDto update(
            @RequestBody @Valid PostUpdateForm form,
            @PathVariable("id") @PostIdExists Long id
    ) {
        return postService.update(form, id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public void deleteById(@PathVariable("id") @PostIdExists Long id) {
        postService.deleteById(id);
    }
}
