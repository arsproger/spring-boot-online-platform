package com.it.academy.services.impl;

import com.it.academy.dao.CommentDao;
import com.it.academy.models.Comment;
import com.it.academy.repositories.CommentRepository;
import com.it.academy.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository repo;
    private final CommentDao commentDao;

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
    public Long save(Comment comment) {
        return repo.save(comment).getId();
    }

    @Override
    public Long deleteById(Long id) {
        repo.deleteById(id);
        return id;
    }

    @Override
    public Long update(Long id, Comment updatedComment) {
        Comment comment = repo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Comment not found with id: " + id));

        comment.setDate(updatedComment.getDate());
        comment.setUser(updatedComment.getUser());
        comment.setTitle(updatedComment.getTitle());
        comment.setDescription(updatedComment.getDescription());
        return repo.save(comment).getId();
    }

    @Override
    public List<Comment> getCourseCommentsByAuthorId(Long authorId) {
        return commentDao.getCourseCommentsByAuthorId(authorId);
    }

    @Override
    public List<Comment> getCommentsByCourseId(Long courseId) {
        return commentDao.getCommentsByCourseId(courseId);
    }
}
