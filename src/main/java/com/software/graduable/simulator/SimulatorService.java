package com.software.graduable.simulator;

import com.software.graduable.course.Course;
import com.software.graduable.course.CourseJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SimulatorService {
    private final CourseJPA courseJPA;

    public SimulatorDto.response.search findSubjectByName(String name){
        Course course = courseJPA.findByCourseName(name)
                .orElseThrow(() -> new RuntimeException("해당 과목 없음."));

        return new SimulatorDto.response.search(course);
    }
}
