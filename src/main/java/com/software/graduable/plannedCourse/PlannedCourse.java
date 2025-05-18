package com.software.graduable.plannedCourse;

import com.software.graduable.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "plannedcourse")
public class PlannedCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long plannedCourseId;

    @ManyToOne
    @JoinColumn(name = "user_Id")
    private User user;  // 유저 연관관계

    private Long semester;    // 해당 과목이 로드맵에서 몇 학기에 들어가는 과목인지
    private Long courseId;  // 해당 과목 리스트에 들어갈 과목 id값

    public PlannedCourse(User user, Long semester, Long courseId) {
        this.user = user;
        this.semester = semester;
        this.courseId = courseId;
    }
}
