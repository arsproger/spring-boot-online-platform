package com.it.academy.services.impl;

import com.it.academy.models.Course;
import com.it.academy.models.Section;
import com.it.academy.repositories.SectionRepository;
import com.it.academy.services.CourseService;
import com.it.academy.services.SectionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SectionServiceImpl implements SectionService {
    private final SectionRepository repo;
    private final CourseService courseService;

    @Override
    public Section getById(Long id) {
        return repo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Section not found with id: " + id));
    }

    @Override
    public List<Section> getAll() {
        return repo.findAll();
    }

    @Override
    public Long save(Long courseId, Section section) {
        Course course = courseService.getById(courseId);

        section.setCourse(course);
        return repo.save(section).getId();
    }

    @Override
    public Long deleteById(Long id) {
        repo.deleteById(id);
        return id;
    }

    @Override
    public Long update(Long id, Section updatedSection) {
        Section section = getById(id);

        section.setName(updatedSection.getName());
        section.setCourse(updatedSection.getCourse());

        return repo.save(section).getId();
    }

}
