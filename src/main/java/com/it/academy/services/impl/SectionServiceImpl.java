package com.it.academy.services.impl;

import com.it.academy.dao.SectionDao;
import com.it.academy.entities.Course;
import com.it.academy.entities.Section;
import com.it.academy.exceptions.AppException;
import com.it.academy.repositories.SectionRepository;
import com.it.academy.services.CourseService;
import com.it.academy.services.SectionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SectionServiceImpl implements SectionService {
    private final SectionRepository sectionRepository;
    private final CourseService courseService;
    private final SectionDao sectionDao;

    @Override
    public Section getById(Long id) {
        return sectionRepository.findById(id).orElseThrow(
                () -> new AppException("Section not found with id: " + id, HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Section> getSectionsByCourse(Long courseId) {
        return sectionDao.getSectionByCourseId(courseId);
    }

    @Override
    public Long create(Long userId, Long courseId, Section section) {
        Course course = courseService.getById(courseId);

        if (!userId.equals(course.getAuthor().getId())) {
            throw new AccessDeniedException("You can't create section for this course!");
        }

        section.setCourse(course);
        section.setName(section.getName());
        return sectionRepository.save(section).getId();
    }

    @Override
    public Long deleteById(Long userId, Long sectionId) {
        Section section = getById(sectionId);

        if (!userId.equals(section.getCourse().getAuthor().getId())) {
            throw new AccessDeniedException("You can't delete this section!");
        }

        sectionRepository.deleteById(sectionId);
        return sectionId;
    }

    @Override
    public Long update(Long userId, Long sectionId, Section updatedSection) {
        Section section = getById(sectionId);

        if (!userId.equals(section.getCourse().getAuthor().getId())) {
            throw new AccessDeniedException("You can't update this section!");
        }

        section.setName(updatedSection.getName());
        return sectionRepository.save(section).getId();
    }

}
