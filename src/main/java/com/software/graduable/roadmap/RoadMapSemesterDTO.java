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
    //전공인지 교양인지
    private boolean category;
    private String yearAndSemester;

    public static RoadMapSemesterDTO toDto(GradeEntity grade) {
        boolean category = grade.getCategory().toCharArray()[0] == '전';
        return RoadMapSemesterDTO.builder()
                .courseName(grade.getCourseName())
                .credit(grade.getCredit())
                .category(category)
                .yearAndSemester(grade.getYearCourseTaken() + "-" + grade.getSemesterCourseTaken())
                .build();
    }

    public static RoadMapSemesterDTO toDto(Course course, User user, PlannedCourse plannedCourse) {
        int remain = user.getUserSemester() - plannedCourse.getSemester().intValue();
        int year = user.getYearOfSemester().intValue() - ((remain) / 2);
        int semester = Math.abs(remain) % 2 != 1 ? 1 : 2;
        String yearAndSemester = year + "-" + semester;

        boolean category = course.getCategory().toCharArray()[0] == '전';


        return RoadMapSemesterDTO.builder()
                .courseName(course.getCourseName())
                .credit(course.getCredit().intValue())
                .category(category)
                .yearAndSemester(yearAndSemester)
                .build();
    }
}
