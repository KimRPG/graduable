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
    private Double credit;
    //전공인지 교양인지
    private boolean category;
    private long semester;



    public static RoadMapSemesterDTO toDto(Course course, PlannedCourse plannedCourse) {

        boolean category = course.getCategory().toCharArray()[0] == '전';


        return RoadMapSemesterDTO.builder()
                .courseName(course.getCourseName())
                .credit(course.getCredit())
                .category(category)
                .semester(plannedCourse.getSemester())
                .build();
    }
}
