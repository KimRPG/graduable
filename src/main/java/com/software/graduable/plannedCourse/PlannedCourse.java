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
    private User user;

    private String semester;
    private Long courseId;
}
