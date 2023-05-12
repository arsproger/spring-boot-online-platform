package com.it.academy.mappers;

import com.it.academy.dto.LessonDto;
import com.it.academy.models.Lesson;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface LessonMapper {
    Lesson map(LessonDto dto);

    LessonDto map(Lesson entity);

    List<LessonDto> map(List<Lesson> entities);
}
