package com.software.graduable.roadmap;

import com.software.graduable.course.Course;
import com.software.graduable.plannedCourse.PlannedCourse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class RoadMapSemesterDTOTest {

    @Test
    @DisplayName("로드맵 학기 DTO 생성 테스트")
    void createRoadMapSemesterDTO() {
        // given
        String courseName = "자바 프로그래밍";
        Double credit = 3.0;
        boolean category = true;
        long semester = 1;
        Long courseID = 1L;

        // when
        RoadMapSemesterDTO dto = RoadMapSemesterDTO.builder()
                .courseName(courseName)
                .credit(credit)
                .category(category)
                .semester(semester)
                .courseID(courseID)
                .build();

        // then
        assertThat(dto.getCourseName()).isEqualTo(courseName);
        assertThat(dto.getCredit()).isEqualTo(credit);
        assertThat(dto.isCategory()).isEqualTo(category);
        assertThat(dto.getSemester()).isEqualTo(semester);
        assertThat(dto.getCourseID()).isEqualTo(courseID);
    }

    @Test
    @DisplayName("Course와 PlannedCourse로부터 DTO 생성 테스트")
    void createDTOFromCourseAndPlannedCourse() {
        // given
        Course course = new Course("자바 프로그래밍", "CS101", "전공필수", 3.0, "전공주제(AI컴퓨터심화)");
        PlannedCourse plannedCourse = new PlannedCourse(null, 1L, 1L);

        // when
        RoadMapSemesterDTO dto = RoadMapSemesterDTO.toDto(course, plannedCourse);

        // then
        assertThat(dto.getCourseName()).isEqualTo(course.getCourseName());
        assertThat(dto.getCredit()).isEqualTo(course.getCredit());
        assertThat(dto.isCategory()).isTrue(); // "전공필수"는 '전'으로 시작하므로 true
        assertThat(dto.getSemester()).isEqualTo(plannedCourse.getSemester());
        assertThat(dto.getCourseID()).isEqualTo(course.getCourseId());
    }
} 