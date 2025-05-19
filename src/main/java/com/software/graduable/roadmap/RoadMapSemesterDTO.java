package com.software.graduable.roadmap;

import com.software.graduable.course.Course;
import com.software.graduable.grade.GradeEntity;
import com.software.graduable.plannedCourse.PlannedCourse;
import com.software.graduable.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RoadMapSemesterDTO {
    // 과목명
    private String courseName;
    // 학점(설계)
    private int credit;
    private String category;
    private String yearAndSemester;

    public static RoadMapSemesterDTO toDto(GradeEntity grade) {
        return RoadMapSemesterDTO.builder()
                .courseName(grade.getCourseName())
                .credit(grade.getCredit())
                .category(grade.getCategory())
                .yearAndSemester(grade.getYearCourseTaken() + "-" + grade.getSemesterCourseTaken())
                .build();
    }

    public static RoadMapSemesterDTO toDto(Course course, User user, PlannedCourse plannedCourse) {
        int remain = user.getUserSemester() - Integer.parseInt(plannedCourse.getSemester());
        int year = 2025 + ((remain) / 2);
        int semester = remain % 2 != 1 ? 1 : 2;
        String yearAndSemester = year + "-" + semester;

        return RoadMapSemesterDTO.builder()
                .courseName(course.getCourseName())
                .credit(course.getCredit().intValue())
                .category(course.getCategory())
                .yearAndSemester(yearAndSemester)
                .build();
    }
}
