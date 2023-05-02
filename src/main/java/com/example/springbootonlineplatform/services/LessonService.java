package com.example.springbootonlineplatform.services;

import com.example.springbootonlineplatform.models.Lesson;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface LessonService {
    Lesson getById(Long id);
    List<Lesson> getAll();
    Long save(Lesson lesson);
    Long deleteById(Long id);
    Long update(Long id, Lesson lesson);
}
