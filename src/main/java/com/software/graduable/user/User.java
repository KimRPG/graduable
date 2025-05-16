package com.software.graduable.user;

import com.software.graduable.plannedCourse.PlannedCourse;
import com.software.graduable.userGrade.UserGrade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<UserGrade> gradeList;  // 유저가 받은 성적 리스트

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<PlannedCourse> plannedCourseList;  // 유저가 학기별 로드맵에 넣어놓은 과목들 리스트

    private String userName;    // 유저 이름
    private String userNickname;    // 유저 닉네임
    private String userSemester;    // 유저 현 학기 수

}
