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
    private User user;

    private Long courseId;
    private Long yearCourseTaken;
    private Long semesterCourseTaken;
    private String gradeStatus;
    private String gradePoint;
    private String graduateCategory;
}
