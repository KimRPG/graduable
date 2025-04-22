package com.software.graduable.grade;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GradeDTO {
    private Category classification;
    private int yearCourseTaken;
    private int semesterCourseTaken;
    private String courseCode;
    private String courseName;
    private int credit;
    private Grade grade;
    private String subjectNote;
}
