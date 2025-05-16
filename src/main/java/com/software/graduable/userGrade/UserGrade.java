package com.software.graduable.userGrade;

import com.software.graduable.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserGrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userGradeId;

    @ManyToOne
    @JoinColumn(name = "user_Id")
    private User user;  // 유저 연관관계

    private Long courseId;  // 해당 성적에 해당하는 과목 idd
    private Long yearCourseTaken;   // 수업을 들은 년도 ex) 2020
    private Long semesterCourseTaken;   // 수업 들은 학기 ex) 1
    private String gradeStatus; // 성적(영어로) ex) B+
    private String gradePoint;  // 성적(숫자로) ex) 3.5


    // 얘는 enum으로 빼도 될듯요
    private String graduateCategory;    // 졸업 구분 ex) 신앙및세계관
}
