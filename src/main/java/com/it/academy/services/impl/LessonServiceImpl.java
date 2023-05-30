package com.it.academy.services.impl;

import com.it.academy.dao.LessonDao;
import com.it.academy.entities.Section;
import com.it.academy.exceptions.AppException;
import com.it.academy.entities.Lesson;
import com.it.academy.repositories.LessonRepository;
import com.it.academy.services.LessonService;
import com.it.academy.services.SectionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;
    private final SectionService sectionService;
    private final LessonDao lessonDao;

    @Override
    public Lesson getById(Long id) {
        return lessonRepository.findById(id).orElseThrow(
                () -> new AppException("Lesson not found with id: " + id, HttpStatus.NOT_FOUND));
    }

    @Override
    public Long create(Long userId, Long sectionId, Lesson lesson) {
        Section section = sectionService.getById(sectionId);

        if(!userId.equals(section.getCourse().getAuthor().getId())) {
            throw new AccessDeniedException("You can't create lesson for this course!");}

        lesson.setSection(sectionService.getById(sectionId));
        return lessonRepository.save(lesson).getId();
    }

    @Override
    public Long deleteById(Long userId, Long lessonId) {
        Lesson lesson = getById(lessonId);

        if (!lesson.getSection().getCourse().getAuthor().getId().equals(userId)) {
            throw new AccessDeniedException("You can't delete this lesson!");}

        lessonRepository.deleteById(lessonId);
        return lessonId;
    }

    @Override
    public Long update(Long userId, Long lessonId, Lesson updatedLesson) {
        Lesson lesson = getById(lessonId);

        if (!lesson.getSection().getCourse().getAuthor().getId().equals(userId)) {
            throw new AccessDeniedException("You can't update this lesson!");}

        lesson.setTitle(updatedLesson.getTitle());
        lesson.setDescription(updatedLesson.getDescription());

        return lessonRepository.save(lesson).getId();
    }

    @Override
    public List<Lesson> getLessonsBySection(Long sectionId) {
        sectionService.getById(sectionId);
        return lessonDao.getLessonsBySection(sectionId);
    }

}
