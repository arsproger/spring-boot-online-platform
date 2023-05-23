package com.it.academy.services.impl;

import com.it.academy.models.Comment;
import com.it.academy.models.Lesson;
import com.it.academy.models.User;
import com.it.academy.repositories.CommentRepository;
import com.it.academy.repositories.LessonRepository;
import com.it.academy.repositories.UserRepository;
import com.it.academy.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final LessonRepository lessonRepository;
    private final UserRepository userRepository;

    @Override
    public Comment getById(Long id) {
        return commentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Comment not found with id: " + id));
    }

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public Long save(Comment comment) {
        return commentRepository.save(comment).getId();
    }

    public Long create(Long userId, Long lessonId, Comment comment) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(
                () -> new EntityNotFoundException("Lesson not found with id: " + lessonId));
        User user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("User not found with id: " + userId));

        comment.setDate(LocalDate.now());
        comment.setLesson(lesson);
        comment.setUser(user);

        return commentRepository.save(comment).getId();
    }

    @Override
    public Long deleteById(Long id) {
        commentRepository.deleteById(id);
        return id;
    }

    @Override
    public Long update(Long id, Comment updatedComment) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Comment not found with id: " + id));

        comment.setTitle(updatedComment.getTitle());
        comment.setDescription(updatedComment.getDescription());

        return commentRepository.save(comment).getId();
    }

}
