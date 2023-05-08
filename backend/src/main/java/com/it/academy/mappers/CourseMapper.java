package com.it.academy.mappers;

import com.it.academy.dto.CourseDto;
import com.it.academy.models.Course;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface CourseMapper {
    Course map(CourseDto dto);

    CourseDto map(Course entity);

    List<CourseDto> map(List<Course> entity);
}
