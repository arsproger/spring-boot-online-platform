package com.it.academy.services.impl;

import com.it.academy.models.Lesson;
import com.it.academy.repositories.LessonRepository;
import com.it.academy.services.LessonService;
import com.it.academy.services.SectionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final LessonRepository repo;
    private final SectionService sectionService;

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
    public Long save(Long sectionId, Lesson lesson) {
        lesson.setSection(sectionService.getById(sectionId));
        return repo.save(lesson).getId();
    }

    @Override
    public Long deleteById(Long id) {
        repo.deleteById(id);
        return id;
    }

    @Override
    public Long update(Long id, Lesson updatedLesson) {
        Lesson lesson = getById(id);

        lesson.setTitle(updatedLesson.getTitle());
        lesson.setDescription(updatedLesson.getDescription());

        return repo.save(lesson).getId();
    }

}
