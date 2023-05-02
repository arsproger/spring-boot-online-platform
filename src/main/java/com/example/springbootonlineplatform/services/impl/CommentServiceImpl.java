package com.example.springbootonlineplatform.services.impl;

import com.example.springbootonlineplatform.models.Comment;
import com.example.springbootonlineplatform.repositories.CommentRepository;
import com.example.springbootonlineplatform.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository repo;

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
}
