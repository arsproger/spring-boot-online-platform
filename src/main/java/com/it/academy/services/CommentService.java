package com.it.academy.services;

import com.it.academy.entities.Comment;

import java.util.List;

public interface CommentService {
    Comment getById(Long id);

    Long create(Long userId, Long lessonId, Comment comment);

    Long deleteById(Long userId, Long id);

    Long update(Long userId, Long commentId, Comment updatedComment);
    List<Comment> getCommentsByLessonId(Long lessonId);
}
