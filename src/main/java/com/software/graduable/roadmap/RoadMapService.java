package com.software.graduable.roadmap;

import com.software.graduable.course.Course;
import com.software.graduable.course.CourseJPA;
import com.software.graduable.global.exception.exceptions.InvalidUserDataException;
import com.software.graduable.grade.GradeEntity;
import com.software.graduable.grade.GradeJPA;
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
public class RoadMapService {
    private final GradeJPA gradeJPA;
    private final UserJPA userJPA;
    private final PlannedCourseJPA plannedCourseJPA;
    private final CourseJPA courseJPA;

    public  List<RoadMapSemesterDTO> getNowSemester(String googleId) {
        User user = userJPA.findByGoogleId(googleId);
        int year = user.getYearOfSemester().intValue();
        int semester = user.getSemesterInYear().intValue();
        return gradeJPA.findByUserAndYearCourseTakenAndSemesterCourseTaken(user, year, semester).stream()
                .map(RoadMapSemesterDTO::toDto)
                .toList();
    }


    public List<Map.Entry<String, List<RoadMapSemesterDTO>>> getAllSemestersWithGradesAndPlannedCourses(String googleId) {
        User user = userJPA.findByGoogleId(googleId);
        if (user == null) {
            throw new InvalidUserDataException("존재하지 않는 사용자입니다: " + googleId);
        }

        // 사용자의 모든 성적을 RoadMapSemesterDTO로 변환
        List<RoadMapSemesterDTO> gradeDTOs = gradeJPA.findByUser(user).stream()
                .map(RoadMapSemesterDTO::toDto)
                .toList();

        // 사용자의 모든 계획된 과목을 RoadMapSemesterDTO로 변환
        List<RoadMapSemesterDTO> plannedCourseDTOs = plannedCourseJPA.findByUser(user).stream()
                .filter(plannedCourse -> plannedCourse.getSemester() >= user.getUserSemester())
                .map(plannedCourse -> {
                    Course course = courseJPA.findById(plannedCourse.getCourseId())
                            .orElse(null);
                    return course != null ?
                            RoadMapSemesterDTO.toDto(course, user, plannedCourse) :
                            null;
                })
                .filter(Objects::nonNull)
                .toList();

        // 두 리스트를 합치고 학기별로 그룹화
        List<RoadMapSemesterDTO> allDTOs = new ArrayList<>();
        allDTOs.addAll(gradeDTOs);
        allDTOs.addAll(plannedCourseDTOs);

        Map<String, List<RoadMapSemesterDTO>> groupedBySemester = allDTOs.stream()
                .collect(Collectors.groupingBy(RoadMapSemesterDTO::getYearAndSemester));

        // 학기 순으로 정렬하여 반환
        return groupedBySemester.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toList());
    }
}
