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
    private List<UserGrade> gradeList;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<PlannedCourse> plannedCourseList;

    private String userName;
    private String userNickname;
    private String userSemester;

}
