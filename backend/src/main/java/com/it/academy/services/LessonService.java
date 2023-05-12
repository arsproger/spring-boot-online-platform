package com.it.academy.services;

import com.it.academy.models.Lesson;

import java.util.List;

public interface LessonService {
    Lesson getById(Long id);

    List<Lesson> getAll();

    Long save(Lesson lesson);

    Long deleteById(Long id);

    Long update(Long id, Lesson lesson);
}
