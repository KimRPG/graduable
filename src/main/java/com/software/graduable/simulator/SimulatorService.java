package com.software.graduable.simulator;

import com.software.graduable.course.Course;
import com.software.graduable.course.CourseJPA;
import com.software.graduable.grade.GradeEntity;
import com.software.graduable.grade.enumFile.Grade;
import com.software.graduable.plannedCourse.PlannedCourse;
import com.software.graduable.plannedCourse.PlannedCourseJPA;
import com.software.graduable.user.User;
import com.software.graduable.user.UserJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SimulatorService {
    private final PlannedCourseJPA plannedCourseJPA;
    private final CourseJPA courseJPA;
    private final UserJPA userJPA;

    public void saveToRoadmap(SimulatorDto.request.saveToRoadmap request) {
        User user = userJPA.findByGoogleId(request.getGoogleId());
        List<Long> courseIdList = request.getCourseIdList();
        Long semester = request.getSemester();

        for (Long courseId : courseIdList) {
            Course course = courseJPA.findById(courseId)
                    .orElseThrow(() -> new RuntimeException("과목 없음 (ID: " + courseId + ")"));

            PlannedCourse plannedCourse = new PlannedCourse(user, semester, courseId);
            plannedCourseJPA.save(plannedCourse); // 여기서 영속화
        }
    }

    public List<SimulatorDto.response.search> findSubjectByName(String name) {
        List<Course> courses = courseJPA.findByCourseNameContainingIgnoreCase(name);

        if (courses.isEmpty()) {
            throw new NoSuchElementException("해당 과목이 없습니다.");
        }

        return courses.stream()
                .map(SimulatorDto.response.search::new)
                .collect(Collectors.toList());
    }

    public SimulatorDto.response.graduateSimulation graduateSimulation(SimulatorDto.request.graduateSimulation request){
        User user = userJPA.findByGoogleId(request.getGoogleId());

        List<Long> semesterList = request.getSemesterList();

        List<PlannedCourse> filteredCourses = user.getPlannedCourseList().stream()
                .filter(plannedCourse -> semesterList.contains(plannedCourse.getSemester()))
                .toList();

        Map<String, Double> categoryMaxCredits = Map.of(
                "신앙및세계관", 9.0,
                "인성및리더십", 6.0,
                "실무영어", 9.0,
                "전문교양", 5.0,
                "BSM", 18.0,
                "ICT융합기초", 2.0,
                "자유선택(교양)", 9.0,
                "자유선택(교양또는비교양)", 0.0,
                "전공주제(AI컴퓨터심화)", 60.0
        );

        Map<String, Double> categoryAttendedCredits = new HashMap<>();
        for (PlannedCourse course : filteredCourses) {
            Course courseEntity = courseJPA.findById(course.getCourseId())
                    .orElseThrow(() -> new RuntimeException("해당 과목 없음 (ID: " + course.getCourseId() + ")"));

            String category = courseEntity.getCourseCategory();

            System.out.println(category);
            Double credit = courseJPA.findById(course.getCourseId()).get().getCredit();

            categoryAttendedCredits.put(
                    category,
                    categoryAttendedCredits.getOrDefault(category, 0.0) + credit
            );
        }

        List<GradeEntity> userGrades = user.getGradeList();
        for (GradeEntity grade : userGrades) {
            String category = grade.getClassification();

            System.out.println(category);
            Double credit = (double) grade.getCredit();

            categoryAttendedCredits.put(
                    category,
                    categoryAttendedCredits.getOrDefault(category, 0.0) + credit
            );
        }

        List<SimulatorDto.response.graduateSimulation.CategoryData> categoryDataList = new ArrayList<>();
        for (String category : categoryAttendedCredits.keySet()) {
            Double attended = categoryAttendedCredits.get(category);
            System.out.println(category + " : " + attended);
            Double max = categoryMaxCredits.getOrDefault(category, attended); // maxCredit 없으면 그대로 사용
            boolean isFinished = attended >= max;

            SimulatorDto.response.graduateSimulation.CategoryData categoryData = new SimulatorDto.response.graduateSimulation.CategoryData();
            categoryData.setName(category);
            categoryData.setAttendedCredit(attended);
            categoryData.setMaxCredit(max);
            categoryData.setIsFinished(isFinished);

            categoryDataList.add(categoryData);
        }

        // 총합 계산
        Double totalMaxCredit = 130.0;
        Double totalAttendedCredit = categoryDataList.stream().mapToDouble(SimulatorDto.response.graduateSimulation.CategoryData::getAttendedCredit).sum();

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
