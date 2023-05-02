package com.example.springbootonlineplatform.mappers;

import com.example.springbootonlineplatform.dtos.LessonDto;
import com.example.springbootonlineplatform.models.Lesson;
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
