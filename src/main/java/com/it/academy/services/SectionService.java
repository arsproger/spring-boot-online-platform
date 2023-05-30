package com.it.academy.services;

import com.it.academy.entities.Section;

import java.util.List;

public interface SectionService {
    Section getById(Long id);

    List<Section> getSectionsByCourse(Long courseId);

    Long create(Long userId, Long courseId, Section section);

    Long deleteById(Long userId, Long sectionId);

    Long update(Long userId, Long sectionId, Section updatedSection);
}
