package com.software.graduable.plannedCourse;

import com.software.graduable.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class PlannedCourseTest {

    @Test
    @DisplayName("계획된 과목 생성 테스트")
    void createPlannedCourse() {
        // given
        User user = User.builder()
                .googleId("google123")
                .email("test@example.com")
                .userName("홍길동")
                .build();
        Long semester = 3L;
        Long courseId = 1L;

        // when
        PlannedCourse plannedCourse = new PlannedCourse(user, semester, courseId);

        // then
        assertThat(plannedCourse.getUser()).isEqualTo(user);
        assertThat(plannedCourse.getSemester()).isEqualTo(semester);
        assertThat(plannedCourse.getCourseId()).isEqualTo(courseId);
    }

    @Test
    @DisplayName("계획된 과목 생성자 테스트")
    void plannedCourseConstructor() {
        // given
        User user = User.builder()
                .googleId("google123")
                .email("test@example.com")
                .userName("홍길동")
                .build();
        Long semester = 3L;
        Long courseId = 1L;

        // when
        PlannedCourse plannedCourse = new PlannedCourse(user, semester, courseId);

        // then
        assertThat(plannedCourse.getUser()).isEqualTo(user);
        assertThat(plannedCourse.getSemester()).isEqualTo(semester);
        assertThat(plannedCourse.getCourseId()).isEqualTo(courseId);
    }
} 