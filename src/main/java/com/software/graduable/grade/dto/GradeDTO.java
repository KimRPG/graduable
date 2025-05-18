package com.software.graduable.grade.dto;

import com.software.graduable.grade.enumFile.Category;
import com.software.graduable.grade.enumFile.Classification;
import com.software.graduable.grade.enumFile.Grade;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GradeDTO {
    //항목
    private Classification classification;
    //구분
    private Category category;
    // 연도
    private int yearCourseTaken;
    // 학기
    private int semesterCourseTaken;
    // 과목코드
    private String courseCode;
    // 과목명
    private String courseName;
    // 학점(설계)
    private Double credit;
    // 성적
    private Grade grade;
    // 비고
    private String subjectNote;
}
