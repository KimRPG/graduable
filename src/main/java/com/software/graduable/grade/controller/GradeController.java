package com.software.graduable.grade.controller;

import com.software.graduable.grade.GradeEntity;
import com.software.graduable.grade.dto.GradeDTO;
import com.software.graduable.grade.dto.GradeOutputDTO;
import com.software.graduable.grade.enumFile.Category;
import com.software.graduable.grade.enumFile.Classification;
import com.software.graduable.grade.enumFile.Grade;
import com.software.graduable.grade.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/grade")
public class GradeController {
    private final GradeService gradeService;

    @PostMapping("/{googleId}")
    public void calculateGrade(
            @PathVariable String googleId, @RequestBody String text) throws Exception {
        gradeService.addGrade(googleId, text);
    }

    @GetMapping("/{googleId}")
    public List<GradeOutputDTO> getGrade(
            @PathVariable String googleId) throws Exception {
        return gradeService.getAllGrades(googleId);
    }

    @DeleteMapping("/{googleId}")
    public void deleteAllGrades(@PathVariable String googleId) {
        gradeService.deleteAllGrades(googleId);
    }
} 