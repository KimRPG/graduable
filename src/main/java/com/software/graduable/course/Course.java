package com.software.graduable.course;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    private String courseName;  // 과목 이름
    private String courseCode;  // 과목 코드
    private String category;    // 과목 구분 ex) 교선필
    private Double credit;    // 학점 수
    private String subjectNote; // 홈페이지상 '기타' 항목에 들어가있는 내용 ex) ICT, 설계 1
    private String courseCategory; // 졸업 구분 (신앙및세계관, 인성및리더십, 실무영어, 전문교양, BSM, ICT융합기초, 자유교양(선택), 전공주제(AI컴퓨터심화))

    private String courseTime;  // 수업시간 언젠지 ex) 원5, 목5
    private String courseLectureRoom;   // 수업 장소 어딘지 ex) OH401
    private String courseGradingType;   // Grade인지 P/F인지 ex) Grade
    private String courseProfessor; // 교수님 성함 ex) JC
    private String courseEnglish;   // 영어 여부 ex) Y

    public Course(String courseName, String courseCode, String category, Double credit, String courseCategory) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.category = category;
        this.credit = credit;
        this.courseCategory = courseCategory;
    }
}
