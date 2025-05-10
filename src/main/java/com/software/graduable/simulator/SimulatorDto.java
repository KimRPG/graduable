package com.software.graduable.simulator;

import com.software.graduable.course.Course;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class SimulatorDto {

    public class request{
        @Setter
        @Getter
        public static class graduateSimulation{
            private String googleId;
            private List<String> semesterList;
        }

        @Getter
        @Setter
        public static class saveToRoadmap{
            private String googleId;
            private List<String> courseIdList;
            private Long semester;
        }
    }

    public static class response{
        @Setter
        @Getter
        public static class search{
            private String id;
            private String time;
            private String name;
            private Double credit;
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
                this.gradeType = null;
                this.isEnglish = null;
                this.year = null;
                this.semester = null;

//                this.gradeType = course.getCourseGradingType().equals("Grade") ? Boolean.TRUE : Boolean.FALSE;
//                this.isEnglish = course.getCourseEnglish().equals("Y") ? Boolean.TRUE : Boolean.FALSE;
//                this.year = 1234567890L;
//                this.semester = 1234567890L;
            }
        }

        @Getter
        @Setter
        public static class graduateSimulation{
            private List<CategoryData> categoryData; // categoryData는 여러 개일 수 있으니까 List로
            private Double totalCredit;
            private Double attendedCredit;
            private Double attendedCreditPercent;
            private Double leftCreditPercent;

            @Getter
            @Setter
            public static class CategoryData {
                private String name;
                private Double maxCredit;
                private Double attendedCredit;
                private Boolean isFinished;
            }

            public graduateSimulation(List<CategoryData> categoryData, Double totalCredit, Double attendedCredit, Double attendedCreditPercent, Double leftCreditPercent) {
                this.categoryData = categoryData;
                this.totalCredit = totalCredit;
                this.attendedCredit = attendedCredit;
                this.attendedCreditPercent = attendedCreditPercent;
                this.leftCreditPercent = leftCreditPercent;
            }
        }
    }
}
