package com.it.academy.services;

import com.it.academy.models.Section;

import java.util.List;

public interface SectionService {
    Section getById(Long id);

    List<Section> getAll();

    Long save(Long courseId, Section subscription);

    Long deleteById(Long id);

    Long update(Long id, Section subscription);
}
