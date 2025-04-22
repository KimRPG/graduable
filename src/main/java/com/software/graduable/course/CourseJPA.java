package com.software.graduable.course;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseJPA extends JpaRepository<Course, Long> {
}
