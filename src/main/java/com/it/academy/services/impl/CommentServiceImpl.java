package com.it.academy.services.impl;

import com.it.academy.models.Comment;
import com.it.academy.models.Lesson;
import com.it.academy.models.User;
import com.it.academy.repositories.CommentRepository;
import com.it.academy.services.CommentService;
import com.it.academy.services.LessonService;
import com.it.academy.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
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
                () -> new EntityNotFoundException("Comment not found with id: " + id));
    }

    @Override
    public List<Comment> getAll() {
        return repo.findAll();
    }

    @Override
    public Long save(Long userId, Long lessonId, Comment comment) {
        Lesson lesson = lessonService.getById(lessonId);
        User user = userService.getById(userId);

        comment.setDate(LocalDate.now());
        comment.setLesson(lesson);
        comment.setUser(user);

        return repo.save(comment).getId();
    }

    @Override
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
