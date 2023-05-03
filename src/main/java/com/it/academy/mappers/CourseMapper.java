package com.it.academy.mappers;

import com.it.academy.dtos.CourseDto;
import com.it.academy.models.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    @Mapping(target = "category", source = "dto.category")
    @Mapping(target = "lessons", ignore = true)
    Course map(CourseDto dto);
    @Mapping(target = "category", source = "entity.category")
    CourseDto map(Course entity);
    List<CourseDto> map(List<Course> entity);
}
