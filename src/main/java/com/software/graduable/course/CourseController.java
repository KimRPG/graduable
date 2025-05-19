package com.software.graduable.course;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;

    @GetMapping("")
    public ResponseEntity<?> findAllCourses(){
        List<Course> response = courseService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/insertAllData")
    public ResponseEntity<?> insertAllData(){
        courseService.insertAllData();
        return ResponseEntity.ok(true);
    }
}
