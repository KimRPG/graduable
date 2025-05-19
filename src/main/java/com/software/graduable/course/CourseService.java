package com.software.graduable.course;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseJPA courseJPA;

    public List<Course> findAll() {
        return courseJPA.findAll();
    }

}
