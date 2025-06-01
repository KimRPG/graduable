package com.software.graduable.course;

import com.software.graduable.config.IntegrationTestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = IntegrationTestConfig.class)
class CourseTest {

    @Test
    @DisplayName("기본 생성자로 과목 생성 테스트")
    void createCourseWithDefaultConstructor() {
        // given
        String courseName = "자바 프로그래밍";
        String courseCode = "CS101";
        String category = "전공필수";
        Double credit = 3.0;
        String courseCategory = "전공주제(AI컴퓨터심화)";

        // when
        Course course = new Course(courseName, courseCode, category, credit, courseCategory);

        // then
        assertThat(course.getCourseName()).isEqualTo(courseName);
        assertThat(course.getCourseCode()).isEqualTo(courseCode);
        assertThat(course.getCategory()).isEqualTo(category);
        assertThat(course.getCredit()).isEqualTo(credit);
        assertThat(course.getCourseCategory()).isEqualTo(courseCategory);
    }

    @Test
    @DisplayName("빈 생성자로 과목 생성 테스트")
    void createCourseWithEmptyConstructor() {
        // when
        Course course = new Course();

        // then
        assertThat(course).isNotNull();
    }

    @Test
    @DisplayName("필수 필드만으로 과목 생성 테스트")
    void createCourseWithRequiredFields() {
        // given
        String courseName = "자바 프로그래밍";
        String courseCode = "CS101";
        String category = "전공필수";
        Double credit = 3.0;
        String courseCategory = "전공주제(AI컴퓨터심화)";

        // when
        Course course = new Course(courseName, courseCode, category, credit, courseCategory);

        // then
        assertThat(course.getCourseName()).isEqualTo(courseName);
        assertThat(course.getCourseCode()).isEqualTo(courseCode);
        assertThat(course.getCategory()).isEqualTo(category);
        assertThat(course.getCredit()).isEqualTo(credit);
        assertThat(course.getCourseCategory()).isEqualTo(courseCategory);
    }
} 