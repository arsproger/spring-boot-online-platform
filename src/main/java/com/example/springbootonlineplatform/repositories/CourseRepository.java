package com.example.springbootonlineplatform.repositories;

import com.example.springbootonlineplatform.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
