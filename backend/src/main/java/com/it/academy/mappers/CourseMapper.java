package com.it.academy.mappers;

import com.it.academy.dtos.CourseDto;
import com.it.academy.models.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface CourseMapper {
    @Mapping(target = "category", source = "category")
    @Mapping(target = "sections", ignore = true)
    Course map(CourseDto dto);

    @Mapping(target = "category", source = "category")
    CourseDto map(Course entity);

    List<CourseDto> map(List<Course> entity);
}
