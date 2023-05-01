package com.example.springbootonlineplatform.services;

import com.example.springbootonlineplatform.models.Comment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CommentService {
    Comment getById(Long id);
    List<Comment> getAll();
    Long save(Comment comment);
    void deleteById(Long id);
    Long update(Long id, Comment comment);
}
