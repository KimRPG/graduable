package com.software.graduable.plannedCourse;

import com.software.graduable.grade.GradeEntity;
import com.software.graduable.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlannedCourseJPA extends JpaRepository<PlannedCourse, Long> {
    List<PlannedCourse> findByUser(User user);
}
