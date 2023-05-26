package com.it.academy.repositories;

import com.it.academy.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByAuthorId(Long id);

    List<Course> findByLanguage(String language);
    List<Course> getCourseByName(String name);

}
