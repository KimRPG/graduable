package com.software.graduable.grade;

import com.software.graduable.grade.dto.GradeDTO;
import com.software.graduable.grade.enumFile.Classification;
import com.software.graduable.grade.enumFile.Category;
import com.software.graduable.grade.enumFile.Grade;
import com.software.graduable.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class GradeEntityTest {

    @Test
    @DisplayName("성적 엔티티 생성 테스트")
    void createGradeEntity() {
        // given
        User user = User.builder()
                .googleId("google123")
                .email("test@example.com")
                .userName("홍길동")
                .build();
        String classification = "전공주제(AI컴퓨터심화)";
        String category = "전필";
        int yearCourseTaken = 2024;
        int semesterCourseTaken = 1;
        String courseCode = "CS101";
        String courseName = "자바 프로그래밍";
        Double credit = 3.0;
        String grade = "A+";
        String subjectNote = "ICT";

        // when
        GradeEntity gradeEntity = GradeEntity.builder()
                .user(user)
                .classification(classification)
                .category(category)
                .yearCourseTaken(yearCourseTaken)
                .semesterCourseTaken(semesterCourseTaken)
                .courseCode(courseCode)
                .courseName(courseName)
                .credit(credit)
                .grade(grade)
                .subjectNote(subjectNote)
                .build();

        // then
        assertThat(gradeEntity.getUser()).isEqualTo(user);
        assertThat(gradeEntity.getClassification()).isEqualTo(classification);
        assertThat(gradeEntity.getCategory()).isEqualTo(category);
        assertThat(gradeEntity.getYearCourseTaken()).isEqualTo(yearCourseTaken);
        assertThat(gradeEntity.getSemesterCourseTaken()).isEqualTo(semesterCourseTaken);
        assertThat(gradeEntity.getCourseCode()).isEqualTo(courseCode);
        assertThat(gradeEntity.getCourseName()).isEqualTo(courseName);
        assertThat(gradeEntity.getCredit()).isEqualTo(credit);
        assertThat(gradeEntity.getGrade()).isEqualTo(grade);
        assertThat(gradeEntity.getSubjectNote()).isEqualTo(subjectNote);
    }

    @Test
    @DisplayName("DTO로부터 성적 엔티티 생성 테스트")
    void createGradeEntityFromDTO() {
        // given
        GradeDTO dto = GradeDTO.builder()
                .classification(Classification.MAJOR)
                .category(Category.MAJOR_COMPULSORY)
                .yearCourseTaken(2024)
                .semesterCourseTaken(1)
                .courseCode("CS101")
                .courseName("자바 프로그래밍")
                .credit(3.0)
                .grade(Grade.A_PLUS)
                .subjectNote("ICT")
                .build();

        // when
        GradeEntity gradeEntity = new GradeEntity().toEntity(dto);

        // then
        assertThat(gradeEntity.getClassification()).isEqualTo(dto.getClassification().getStatus());
        assertThat(gradeEntity.getCategory()).isEqualTo(dto.getCategory().getStatus());
        assertThat(gradeEntity.getYearCourseTaken()).isEqualTo(dto.getYearCourseTaken());
        assertThat(gradeEntity.getSemesterCourseTaken()).isEqualTo(dto.getSemesterCourseTaken());
        assertThat(gradeEntity.getCourseCode()).isEqualTo(dto.getCourseCode());
        assertThat(gradeEntity.getCourseName()).isEqualTo(dto.getCourseName());
        assertThat(gradeEntity.getCredit()).isEqualTo(dto.getCredit());
        assertThat(gradeEntity.getGrade()).isEqualTo(dto.getGrade().getStatus());
        assertThat(gradeEntity.getSubjectNote()).isEqualTo(dto.getSubjectNote());
    }
} 