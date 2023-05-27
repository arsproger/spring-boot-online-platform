package com.it.academy.services;

import com.it.academy.models.Comment;

import java.util.List;

public interface CommentService {
    Comment getById(Long id);

    List<Comment> getAll();
    Long create(Long userId, Long lessonId, Comment comment);

    Long deleteById(Long id);

    Long update(Long id, Comment comment);
    List<Comment> getCommentsByLessonId(Long lessonId);
}
