package com.it.academy.mappers;

import com.it.academy.dtos.LessonDto;
import com.it.academy.models.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LessonMapper {
    @Mapping(source = "courseId", target = "course.id")
    Lesson map(LessonDto dto);
    @Mapping(source = "course.id", target = "courseId")
    LessonDto map(Lesson entity);
    List<LessonDto> map(List<Lesson> entities);
}
