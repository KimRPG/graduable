package com.software.graduable.grade;

import com.software.graduable.grade.enumFile.Category;
import com.software.graduable.grade.enumFile.Classification;
import com.software.graduable.grade.enumFile.Grade;
import com.software.graduable.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GradeJPA extends JpaRepository<GradeEntity, Long> {
    // 사용자의 모든 성적 조회
    List<GradeEntity> findByUser(User user);

    // 사용자의 특정 학기 성적 조회
    List<GradeEntity> findByUserAndYearCourseTakenAndSemesterCourseTaken(
            User user, int yearCourseTaken, int semesterCourseTaken);

    // 사용자의 특정 카테고리 성적 조회
    List<GradeEntity> findByUserAndCategory(User user, Category category);

    // 사용자의 특정 분류 성적 조회
    List<GradeEntity> findByUserAndClassification(User user, Classification classification);

    // 사용자의 특정 성적 등급 조회
    List<GradeEntity> findByUserAndGrade(User user, Grade grade);

    // 사용자의 총 이수 학점 계산
    @Query("SELECT SUM(g.credit) FROM GradeEntity g WHERE g.user = :user")
    int calculateTotalCredits(@Param("user") User user);

    // 사용자의 특정 카테고리 이수 학점 계산
    @Query("SELECT SUM(g.credit) FROM GradeEntity g WHERE g.user = :user AND g.category = :category")
    int calculateCategoryCredits(@Param("user") User user, @Param("category") Category category);
} 