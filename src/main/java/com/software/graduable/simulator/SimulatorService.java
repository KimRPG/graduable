package com.software.graduable.simulator;

import com.software.graduable.course.Course;
import com.software.graduable.course.CourseJPA;
import com.software.graduable.grade.GradeEntity;
import com.software.graduable.grade.enumFile.Grade;
import com.software.graduable.plannedCourse.PlannedCourse;
import com.software.graduable.user.User;
import com.software.graduable.user.UserJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SimulatorService {
    private final CourseJPA courseJPA;
    private final UserJPA userJPA;

    public SimulatorDto.response.search findSubjectByName(String name){
        Course course = courseJPA.findByCourseName(name)
                .orElseThrow(() -> new RuntimeException("해당 과목 없음."));

        return new SimulatorDto.response.search(course);
    }

    public SimulatorDto.response.graduateSimulation graduateSimulation(SimulatorDto.request.graduateSimulation request){
        User user = userJPA.findByGoogleId(request.getGoogleId());

        List<String> semesterList = request.getSemesterList();

        List<PlannedCourse> filteredCourses = user.getPlannedCourseList().stream()
                .filter(plannedCourse -> semesterList.contains(plannedCourse.getSemester()))
                .toList();

        Map<String, Long> categoryMaxCredits = Map.of(
                "신앙및세계관", 9L,
                "인성및리더십", 6L,
                "실무영어", 9L,
                "전문교양", 5L,
                "BSM", 18L,
                "ICT융합기초", 2L,
                "자유선택(교양)", 9L,
                "자유선택(교양또는비교양)", 0L,
                "전공주제(AI컴퓨터심화)", 60L
        );

        Map<String, Long> categoryAttendedCredits = new HashMap<>();
        for (PlannedCourse course : filteredCourses) {
            Course courseEntity = courseJPA.findById(course.getCourseId())
                    .orElseThrow(() -> new RuntimeException("해당 과목 없음 (ID: " + course.getCourseId() + ")"));

            String category = courseEntity.getCourseCategory();

            System.out.println(category);
            Long credit = courseJPA.findById(course.getCourseId()).get().getCredit();

            categoryAttendedCredits.put(
                    category,
                    categoryAttendedCredits.getOrDefault(category, 0L) + credit
            );
        }

        List<SimulatorDto.response.graduateSimulation.CategoryData> categoryDataList = new ArrayList<>();
        for (String category : categoryAttendedCredits.keySet()) {
            Long attended = categoryAttendedCredits.get(category);
            System.out.println(category + " : " + attended);
            Long max = categoryMaxCredits.getOrDefault(category, attended); // maxCredit 없으면 그대로 사용
            boolean isFinished = attended >= max;

            SimulatorDto.response.graduateSimulation.CategoryData categoryData = new SimulatorDto.response.graduateSimulation.CategoryData();
            categoryData.setName(category);
            categoryData.setAttendedCredit(attended);
            categoryData.setMaxCredit(max);
            categoryData.setIsFinished(isFinished);

            categoryDataList.add(categoryData);
        }

        // 총합 계산
        Long totalMaxCredit = 130L;
        Long totalAttendedCredit = categoryDataList.stream().mapToLong(SimulatorDto.response.graduateSimulation.CategoryData::getAttendedCredit).sum();

        double attendedPercent = (totalMaxCredit == 0) ? 0.0 : (totalAttendedCredit * 100.0 / totalMaxCredit);
        double leftPercent = 100.0 - attendedPercent;

        return new SimulatorDto.response.graduateSimulation(
                categoryDataList,
                totalMaxCredit,
                totalAttendedCredit,
                attendedPercent,
                leftPercent
        );
    }
}
