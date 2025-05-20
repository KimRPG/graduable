package com.software.graduable.grade.service;

import com.software.graduable.course.Course;
import com.software.graduable.course.CourseJPA;
import com.software.graduable.grade.GradeEntity;
import com.software.graduable.grade.GradeJPA;
import com.software.graduable.grade.dto.GradeDTO;
import com.software.graduable.grade.dto.GradeOutputDTO;
import com.software.graduable.grade.enumFile.Category;
import com.software.graduable.grade.enumFile.Classification;
import com.software.graduable.grade.enumFile.Grade;
import com.software.graduable.global.exception.exceptions.InvalidUserDataException;
import com.software.graduable.plannedCourse.PlannedCourse;
import com.software.graduable.plannedCourse.PlannedCourseJPA;
import com.software.graduable.plannedCourse.YearSemester;
import com.software.graduable.user.User;
import com.software.graduable.user.UserJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GradeService {
    private final GradeJPA gradeJPA;
    private final UserJPA userJPA;
    private final PlannedCourseJPA plannedCourseJPA;
    private final CourseJPA courseJPA;
    private final GradeDTOMapper gradeDTOMapper = new GradeDTOMapper();
    // 성적 등록
    @Transactional
    public void addGrade(String googleId, String text) throws Exception {
        User user = userJPA.findByGoogleId(googleId);
        if (user == null) {
            throw new InvalidUserDataException("존재하지 않는 사용자입니다: " + googleId);
        }

        // Hashset으로 중복 제거하면서 년도와 학기를 넣음
        Set<YearSemester> semesterSet = new HashSet<>();
        List<GradeDTO> gradeDTOList = gradeDTOMapper.parseGradeDTO(text);
        if(gradeJPA.findByUser(user).isEmpty()) {
            for (GradeDTO dto : gradeDTOList) {
                GradeEntity entity = new GradeEntity().toEntity(dto);
                semesterSet.add(new YearSemester(entity.getYearCourseTaken(), entity.getSemesterCourseTaken()));
                entity.setUser(user);
                gradeJPA.save(entity);
            }
        } else {
            gradeJPA.deleteAllByUser(user);
            for (GradeDTO dto : gradeDTOList) {
                GradeEntity entity = new GradeEntity().toEntity(dto);
                entity.setUser(user);
                gradeJPA.save(entity);
            }
        }

        // 정렬
        List<YearSemester> semesterList = new ArrayList<>(semesterSet);
        semesterList.sort((a, b) -> {
            if (a.getYear() != b.getYear()) return Integer.compare(a.getYear(), b.getYear()); // 연도 오름차순
            return Integer.compare(a.getSemester(), b.getSemester());              // 학기 오름차순
        });

        int userIndex = -1;
        for (int i = 0; i < semesterList.size(); i++) {
            YearSemester ys = semesterList.get(i);
            if (ys.getYear() == user.getYearOfSemester() && ys.getSemester() == user.getUserSemester()) {
                userIndex = i;
                break;
            }
        }

        Map<YearSemester, Integer> semesterIndexMap = new LinkedHashMap<>();
        for (int i = 0; i < semesterList.size(); i++) {
            int semesterOrder = (i - userIndex)-1;
            semesterIndexMap.put(semesterList.get(i), semesterOrder);
        }

        List<PlannedCourse> plannedCourseList = new ArrayList<>();
        for (GradeDTO dto : gradeDTOList) {
            GradeEntity entity = new GradeEntity().toEntity(dto);
            int year = entity.getYearCourseTaken();
            int semester = entity.getSemesterCourseTaken(); // 1 or 2

            // 몇 번째 학기인지 확인
            Long semesterOrder = Long.valueOf(semesterIndexMap.getOrDefault(new YearSemester(year, semester), null));

            if (semesterOrder != null) {
                Optional<Course> courseOpt = courseJPA.findByCourseNameContainingIgnoreCase(entity.getCourseName()).stream().findFirst();
                Long courseId = courseOpt.map(Course::getCourseId).orElse(null);

                PlannedCourse plannedCourse = new PlannedCourse(
                        user,                       // 유저 객체
                        semesterOrder,             // 몇 번째 학기인지
                        courseId     // 과목 ID
                );
                plannedCourseList.add(plannedCourse);
            } else {
                System.out.println("⚠️ 매핑되지 않은 학기: " + year + "년 " + semester + "학기");
            }
        }

        plannedCourseJPA.saveAll(plannedCourseList);
    }

    // 사용자의 모든 성적 조회
    public List<GradeOutputDTO> getAllGrades(String googleId) {
        User user = userJPA.findByGoogleId(googleId);
        if (user == null) {
            throw new InvalidUserDataException("존재하지 않는 사용자입니다: " + googleId);
        }

        return gradeJPA.findByUser(user)
                .stream()
                .map(GradeOutputDTO::toDto)
                .collect(Collectors.toList());
    }


    // 특정 학기 성적 조회
    public List<GradeEntity> getGradesBySemester(String googleId, int year, int semester) {
        User user = userJPA.findByGoogleId(googleId);
        if (user == null) {
            throw new InvalidUserDataException("존재하지 않는 사용자입니다: " + googleId);
        }
        return gradeJPA.findByUserAndYearCourseTakenAndSemesterCourseTaken(user, year, semester);
    }

    // 특정 카테고리 성적 조회
    public List<GradeEntity> getGradesByCategory(String googleId, Category category) {
        User user = userJPA.findByGoogleId(googleId);
        if (user == null) {
            throw new InvalidUserDataException("존재하지 않는 사용자입니다: " + googleId);
        }
        return gradeJPA.findByUserAndCategory(user, category);
    }

    // 특정 분류 성적 조회
    public List<GradeEntity> getGradesByClassification(String googleId, Classification classification) {
        User user = userJPA.findByGoogleId(googleId);
        if (user == null) {
            throw new InvalidUserDataException("존재하지 않는 사용자입니다: " + googleId);
        }
        return gradeJPA.findByUserAndClassification(user, classification);
    }

    // 특정 성적 등급 조회
    public List<GradeEntity> getGradesByGrade(String googleId, Grade grade) {
        User user = userJPA.findByGoogleId(googleId);
        if (user == null) {
            throw new InvalidUserDataException("존재하지 않는 사용자입니다: " + googleId);
        }
        return gradeJPA.findByUserAndGrade(user, grade);
    }

    // 총 이수 학점 계산
    public int calculateTotalCredits(String googleId) {
        User user = userJPA.findByGoogleId(googleId);
        if (user == null) {
            throw new InvalidUserDataException("존재하지 않는 사용자입니다: " + googleId);
        }
        return gradeJPA.calculateTotalCredits(user);
    }

    // 특정 카테고리 이수 학점 계산
    public int calculateCategoryCredits(String googleId, Category category) {
        User user = userJPA.findByGoogleId(googleId);
        if (user == null) {
            throw new InvalidUserDataException("존재하지 않는 사용자입니다: " + googleId);
        }
        return gradeJPA.calculateCategoryCredits(user, category);
    }

    // 사용자의 모든 성적 삭제
    @Transactional
    public void deleteAllGrades(String googleId) {
        User user = userJPA.findByGoogleId(googleId);
        if (user == null) {
            throw new InvalidUserDataException("존재하지 않는 사용자입니다: " + googleId);
        }
        gradeJPA.deleteAllByUser(user);
    }
} 