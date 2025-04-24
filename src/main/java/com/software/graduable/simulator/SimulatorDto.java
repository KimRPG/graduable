package com.software.graduable.simulator;

import com.software.graduable.course.Course;
import lombok.Getter;
import lombok.Setter;

public class SimulatorDto {

//    public class request{
//        @Setter
//        @Getter
//        public static class
//    }

    public static class response{
        @Setter
        @Getter
        public static class search{
            private String id;
            private String time;
            private String name;
            private Long credit;
            private String room;
            private String professor;
            private Boolean gradeType;
            private Boolean isEnglish;
            private Long year;
            private Long semester;

            public search(Course course){
                this.id = String.valueOf(course.getCourseId());
                this.time = course.getCourseTime();
                this.name = course.getCourseName();
                this.credit = course.getCredit();
                this.room = course.getCourseLectureRoom();
                this.professor = course.getCourseProfessor();
                this.gradeType = course.getCourseGradingType().equals("Grade") ? Boolean.TRUE : Boolean.FALSE;
                this.isEnglish = course.getCourseEnglish().equals("Y") ? Boolean.TRUE : Boolean.FALSE;
                this.year = 1234567890L;
                this.semester = 1234567890L;
            }
        }
    }
}
