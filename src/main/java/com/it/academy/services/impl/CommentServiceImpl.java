package com.it.academy.services.impl;

import com.it.academy.dao.CommentDao;
import com.it.academy.entities.Comment;
import com.it.academy.entities.Lesson;
import com.it.academy.entities.User;
import com.it.academy.enums.Role;
import com.it.academy.exceptions.AppException;
import com.it.academy.repositories.CommentRepository;
import com.it.academy.services.CommentService;
import com.it.academy.services.LessonService;
import com.it.academy.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final LessonService lessonService;
    private final UserService userService;
    private final CommentDao commentDao;

    @Override
    public Comment getById(Long id) {
        return commentRepository.findById(id).orElseThrow(
                () -> new AppException("Comment not found with id: " + id, HttpStatus.NOT_FOUND));
    }

    @Override
    public Long create(Long userId, Long lessonId, Comment comment) {
        Lesson lesson = lessonService.getById(lessonId);
        User user = userService.getById(userId);

        comment.setDate(LocalDate.now());
        comment.setLesson(lesson);
        comment.setUser(user);

        return commentRepository.save(comment).getId();
    }

    @Override
    public Long deleteById(Long userId, Long commentId) {
        Comment comment = getById(commentId);

        if (!(comment.getUser().getId().equals(userId) ||
                userService.getById(userId).getRole().equals(Role.ROLE_ADMIN))) {
            throw new AccessDeniedException("You can't delete this comment!");
        }

        commentRepository.deleteById(commentId);
        return commentId;
    }

    @Override
    public Long update(Long userId, Long commentId, Comment updatedComment) {
        Comment comment = getById(commentId);

        if (!comment.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("You can't delete this comment!");
        }

        comment.setTitle(updatedComment.getTitle());
        comment.setDescription(updatedComment.getDescription());

        return commentRepository.save(comment).getId();
    }

    @Override
    public List<Comment> getCommentsByLessonId(Long lessonId) {
        lessonService.getById(lessonId);
        return commentDao.getCommentsByLessonId(lessonId);
    }

}
