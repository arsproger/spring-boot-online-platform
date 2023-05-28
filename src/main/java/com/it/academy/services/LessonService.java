package com.it.academy.services;

import com.it.academy.entities.Lesson;

import java.util.List;

public interface LessonService {
    Lesson getById(Long id);

    Long create(Long userId, Long sectionId, Lesson lesson);

    Long deleteById(Long userId, Long lessonId);

    Long update(Long userId, Long lessonId, Lesson updatedLesson);
    List<Lesson> getLessonsBySection(Long sectionId);
}
