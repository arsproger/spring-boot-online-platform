package com.it.academy.services.impl;

import com.it.academy.exceptions.AppException;
import com.it.academy.models.Comment;
import com.it.academy.models.Lesson;
import com.it.academy.models.User;
import com.it.academy.repositories.CommentRepository;
import com.it.academy.services.CommentService;
import com.it.academy.services.LessonService;
import com.it.academy.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository repo;
    private final LessonService lessonService;
    private final UserService userService;

    @Override
    public Comment getById(Long id) {
        return repo.findById(id).orElseThrow(
                () -> new AppException("Comment not found with id: " + id, HttpStatus.NOT_FOUND));
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Comment> getAll() {
        return repo.findAll();
    }

    @Override
    public Long create(Long userId, Long lessonId, Comment comment) {
        Lesson lesson = lessonService.getById(lessonId);
        User user = userService.getById(userId);

        comment.setDate(LocalDate.now());
        comment.setLesson(lesson);
        comment.setUser(user);

        return repo.save(comment).getId();
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Long deleteById(Long id) {
        repo.deleteById(id);
        return id;
    }

    @Override
    public Long update(Long id, Comment updatedComment) {
        Comment comment = getById(id);

        comment.setTitle(updatedComment.getTitle());
        comment.setDescription(updatedComment.getDescription());

        return repo.save(comment).getId();
    }

}
