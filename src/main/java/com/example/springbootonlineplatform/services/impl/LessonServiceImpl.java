package com.example.springbootonlineplatform.services.impl;

import com.example.springbootonlineplatform.models.Lesson;
import com.example.springbootonlineplatform.repositories.LessonRepository;
import com.example.springbootonlineplatform.services.LessonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final LessonRepository repo;

    @Override
    public Lesson getById(Long id) {
        return repo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Lesson not found with id: " + id));
    }

    @Override
    public List<Lesson> getAll() {
        return repo.findAll();
    }

    @Override
    public Long save(Lesson lesson) {
        return repo.save(lesson).getId();
    }

    @Override
    public Long deleteById(Long id) {
        repo.deleteById(id);
        return id;
    }

    @Override
    public Long update(Long id, Lesson updatedLesson) {
        Lesson lesson = repo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Lesson not found with id: " + id));
        lesson.setDescription(updatedLesson.getDescription());
        lesson.setComments(updatedLesson.getComments());
        lesson.setArticles(updatedLesson.getArticles());
        lesson.setCourse(updatedLesson.getCourse());
        lesson.setTitle(updatedLesson.getTitle());
        return repo.save(lesson).getId();
    }
}
