package com.software.graduable.course;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseJPA extends JpaRepository<Course, Long> {
    Optional<Course> findByCourseName(String courseName);
}
