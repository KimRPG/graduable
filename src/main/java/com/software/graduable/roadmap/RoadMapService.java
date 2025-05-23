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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        return plannedCourseJPA.findByUserAndSemester(user,user.getUserSemester()).stream()
                .map(plannedCourse -> {
                    Course course = courseJPA.findById(plannedCourse.getCourseId())
                            .orElse(null);
                    return course != null ?
                            RoadMapSemesterDTO.toDto(course, plannedCourse) :
                            null;
                })
                .toList();
    }


    public List<Map.Entry<Long, List<RoadMapSemesterDTO>>> getAllSemestersWithGradesAndPlannedCourses(String googleId) {
        User user = userJPA.findByGoogleId(googleId);
        if (user == null) {
            throw new InvalidUserDataException("존재하지 않는 사용자입니다: " + googleId);
        }

        // 사용자의 모든 계획된 과목을 RoadMapSemesterDTO로 변환
        List<RoadMapSemesterDTO> plannedCourseDTOs = plannedCourseJPA.findByUser(user).stream()
                .map(plannedCourse -> {
                    Course course = courseJPA.findById(plannedCourse.getCourseId())
                            .orElse(null);
                    return course != null ?
                            RoadMapSemesterDTO.toDto(course, plannedCourse) :
                            null;
                })
                .filter(Objects::nonNull)
                .toList();

        // 두 리스트를 합치고 학기별로 그룹화

        Map<Long, List<RoadMapSemesterDTO>> groupedBySemester = plannedCourseDTOs.stream()
                .collect(Collectors.groupingBy(RoadMapSemesterDTO::getSemester));

        // 학기 순으로 정렬하여 반환
        return groupedBySemester.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toList());
    }

    public ResponseEntity<?> patchRoadMapSemester(String googleId, RoadMapDTO.saveToRoadmap request) {
        User user = userJPA.findByGoogleId(googleId);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There's no such id user");
        }

        List<PlannedCourse> plannedCourseList = plannedCourseJPA.findByUserAndSemester(user, request.getSemester());
        plannedCourseJPA.deleteAll(plannedCourseList);

        List<Long> courseIdList = request.getCourseIdList(); // 새로 들어갈 과목 ID 리스트
        List<PlannedCourse> newCourses = courseIdList.stream()
                .map(courseId -> new PlannedCourse(user, request.getSemester(), courseId))
                .toList();

        plannedCourseJPA.saveAll(newCourses);
        return ResponseEntity.ok("RoadMap updated for semester " + request.getSemester());
    }

    @Transactional
    public void deleteUserAndSemester(String googleId, Long semester) {
        User user = userJPA.findByGoogleId(googleId);
        plannedCourseJPA.deleteByUserAndSemester(user, semester);
    }
}
