package com.software.graduable.grade.dto;

import com.software.graduable.grade.GradeEntity;
import com.software.graduable.grade.enumFile.Category;
import com.software.graduable.grade.enumFile.Classification;
import com.software.graduable.grade.enumFile.Grade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class GradeOutputDTO {
    //항목
    private String classification;
    //구분
    private String category;
    // 연도 + 학기
    private String yearAndSemesterCourseTaken;
    // 과목명
    private String courseName;
    // 학점(설계)
    private int credit;
    // 성적
    private String grade;
    // 비고
    private String subjectNote;

    public static GradeOutputDTO toDto(GradeEntity entity) {
        return GradeOutputDTO.builder()
                .classification(entity.getClassification())
                .category(entity.getCategory())
                .yearAndSemesterCourseTaken(entity.getYearCourseTaken() + "-" + entity.getSemesterCourseTaken())
                .courseName(entity.getCourseName())
                .credit(entity.getCredit())
                .grade(entity.getGrade())
                .subjectNote(entity.getSubjectNote())
                .build();
    }
}
