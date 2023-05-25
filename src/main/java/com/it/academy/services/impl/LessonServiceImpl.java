package com.it.academy.services.impl;

import com.it.academy.exceptions.AppException;
import com.it.academy.models.Lesson;
import com.it.academy.repositories.LessonRepository;
import com.it.academy.services.LessonService;
import com.it.academy.services.SectionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
                () -> new AppException("Lesson not found with id: " + id, HttpStatus.NOT_FOUND));
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Lesson> getAll() {
        return repo.findAll();
    }

    @Override
    public Long save(Long sectionId, Lesson lesson) {
        lesson.setSection(sectionService.getById(sectionId));
        return repo.save(lesson).getId();
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
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
