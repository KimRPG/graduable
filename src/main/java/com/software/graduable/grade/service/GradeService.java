package com.software.graduable.grade.service;

import com.software.graduable.grade.GradeEntity;
import com.software.graduable.grade.GradeJPA;
import com.software.graduable.grade.dto.GradeDTO;
import com.software.graduable.grade.dto.GradeOutputDTO;
import com.software.graduable.grade.enumFile.Category;
import com.software.graduable.grade.enumFile.Classification;
import com.software.graduable.grade.enumFile.Grade;
import com.software.graduable.global.exception.exceptions.InvalidUserDataException;
import com.software.graduable.user.User;
import com.software.graduable.user.UserJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GradeService {
    private final GradeJPA gradeJPA;
    private final UserJPA userJPA;
    private final GradeDTOMapper gradeDTOMapper = new GradeDTOMapper();
    // 성적 등록
    @Transactional
    public void addGrade(String googleId, String text) throws Exception {
        User user = userJPA.findByGoogleId(googleId);
        if (user == null) {
            throw new InvalidUserDataException("존재하지 않는 사용자입니다: " + googleId);
        }
        List<GradeDTO> gradeDTOList = gradeDTOMapper.parseGradeDTO(text);
        for (GradeDTO dto : gradeDTOList) {
            GradeEntity entity = new GradeEntity().toEntity(dto);
            entity.setUser(user);
            gradeJPA.save(entity);
        }
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
} 