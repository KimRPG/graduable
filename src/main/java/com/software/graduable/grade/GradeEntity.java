package com.software.graduable.grade;

import com.software.graduable.grade.dto.GradeDTO;
import com.software.graduable.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "grade_table")
public class GradeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gradeId;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;  // 성적을 받은 사용자

    @Column(name = "classification")
    private String classification;  // 항목

    @Column(name = "category")
    private String category;  // 구분

    private int yearCourseTaken;  // 연도
    private int semesterCourseTaken;  // 학기
    private String courseCode;  // 과목코드
    private String courseName;  // 과목명
    private int credit;  // 학점(설계)

    @Column(name = "grade")
    private String grade;  // 성적

    private String subjectNote;  // 비고

    public GradeEntity toEntity(GradeDTO dto) {
        return GradeEntity.builder()
                .classification(dto.getClassification().getStatus())
                .category(dto.getCategory().getStatus())
                .yearCourseTaken(dto.getYearCourseTaken())
                .semesterCourseTaken(dto.getSemesterCourseTaken())
                .courseCode(dto.getCourseCode())
                .courseName(dto.getCourseName())
                .credit(dto.getCredit())
                .grade(dto.getGrade().getStatus())
                .subjectNote(dto.getSubjectNote())
                .build();
    }
} 