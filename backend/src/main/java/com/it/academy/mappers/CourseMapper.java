package com.it.academy.mappers;

import com.it.academy.dtos.CourseDto;
import com.it.academy.models.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface CourseMapper {
    @Mapping(source = "categoryTitle", target = "category.title")
    Course map(CourseDto dto);

    @Mapping(source = "category.title", target = "categoryTitle")
    CourseDto map(Course entity);

    List<CourseDto> map(List<Course> entity);
}
