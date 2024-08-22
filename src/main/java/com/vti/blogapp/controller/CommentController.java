package com.vti.blogapp.controller;

import com.vti.blogapp.dto.CommentDto;
import com.vti.blogapp.form.CommentCreateForm;
import com.vti.blogapp.form.CommentFilterForm;
import com.vti.blogapp.form.CommentUpdateForm;
import com.vti.blogapp.service.CommentService;
import com.vti.blogapp.validation.CommentIdExists;
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
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/api/v1/comments")
    public Page<CommentDto> findAll(CommentFilterForm form, Pageable pageable) {
        return commentService.findAll(form, pageable);
    }

    @GetMapping("/api/v1/posts/{postId}/comments")
    public Page<CommentDto> findByPostId(
            @PathVariable("postId") @PostIdExists Long postId,
            Pageable pageable
    ) {
        return commentService.findByPostId(postId, pageable);
    }

    @GetMapping("/api/v1/comments/{id}")
    public CommentDto findById(@PathVariable("id") @CommentIdExists Long id) {
        return commentService.findById(id);
    }

    @PostMapping("/api/v1/posts/{postId}/comments")
    public CommentDto create(
            @RequestBody @Valid CommentCreateForm form,
            @PathVariable("postId") Long postId
    ) {
        return commentService.create(form, postId);
    }

    @PutMapping("/api/v1/comments/{id}")
    public CommentDto update(
            @RequestBody @Valid CommentUpdateForm form,
            @PathVariable("id") @CommentIdExists Long id
    ) {
        return commentService.update(form, id);
    }

    @DeleteMapping("/api/v1/comments/{id}")
    public void deleteById(@PathVariable("id") @CommentIdExists Long id) {
        commentService.deleteById(id);
    }

    @DeleteMapping("/api/v1/comments/email/{email}")
    public void deleteByEmail(@PathVariable("email") String email) {
        commentService.deleteByEmail(email);
    }
}
