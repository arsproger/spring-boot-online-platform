package com.it.academy.mappers;

import com.it.academy.dto.CourseDto;
import com.it.academy.entities.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface CourseMapper {
    @Mapping(source = "author", target = "author.fullName")
    Course map(CourseDto dto);

    @Mapping(source = "author.fullName", target = "author")
    CourseDto map(Course entity);

    List<CourseDto> map(List<Course> entity);
}
