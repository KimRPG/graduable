package com.software.graduable.simulator;

import com.software.graduable.course.Course;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

class SimulatorDtoTest {

    @Test
    @DisplayName("졸업 시뮬레이션 요청 DTO 생성 테스트")
    void createGraduateSimulationRequest() {
        // given
        String googleId = "google123";
        List<Long> semesterList = Arrays.asList(1L, 2L, 3L);

        // when
        SimulatorDto.request.graduateSimulation request = new SimulatorDto.request.graduateSimulation();
        request.setGoogleId(googleId);
        request.setSemesterList(semesterList);

        // then
        assertThat(request.getGoogleId()).isEqualTo(googleId);
        assertThat(request.getSemesterList()).isEqualTo(semesterList);
    }

    @Test
    @DisplayName("로드맵 저장 요청 DTO 생성 테스트")
    void createSaveToRoadmapRequest() {
        // given
        String googleId = "google123";
        List<Long> courseIdList = Arrays.asList(1L, 2L, 3L);
        Long semester = 1L;

        // when
        SimulatorDto.request.saveToRoadmap request = new SimulatorDto.request.saveToRoadmap();
        request.setGoogleId(googleId);
        request.setCourseIdList(courseIdList);
        request.setSemester(semester);

        // then
        assertThat(request.getGoogleId()).isEqualTo(googleId);
        assertThat(request.getCourseIdList()).isEqualTo(courseIdList);
        assertThat(request.getSemester()).isEqualTo(semester);
    }

    @Test
    @DisplayName("과목 검색 응답 DTO 생성 테스트")
    void createSearchResponse() {
        // given
        Course course = new Course("자바 프로그래밍", "CS101", "전공필수", 3.0, "전공주제(AI컴퓨터심화)");

        // when
        SimulatorDto.response.search response = new SimulatorDto.response.search(course);

        // then
        assertThat(response.getId()).isEqualTo(String.valueOf(course.getCourseId()));
        assertThat(response.getName()).isEqualTo(course.getCourseName());
        assertThat(response.getCredit()).isEqualTo(course.getCredit());
        assertThat(response.getGradeType()).isNull();
        assertThat(response.getIsEnglish()).isNull();
        assertThat(response.getYear()).isNull();
        assertThat(response.getSemester()).isNull();
    }

    @Test
    @DisplayName("졸업 시뮬레이션 응답 DTO 생성 테스트")
    void createGraduateSimulationResponse() {
        // given
        List<SimulatorDto.response.graduateSimulation.CategoryData> categoryDataList = Arrays.asList(
            new SimulatorDto.response.graduateSimulation.CategoryData() {{
                setName("전공필수");
                setMaxCredit(30.0);
                setAttendedCredit(24.0);
                setIsFinished(false);
            }},
            new SimulatorDto.response.graduateSimulation.CategoryData() {{
                setName("교양필수");
                setMaxCredit(20.0);
                setAttendedCredit(20.0);
                setIsFinished(true);
            }}
        );
        Double totalCredit = 50.0;
        Double attendedCredit = 44.0;
        Double attendedCreditPercent = 88.0;
        Double leftCreditPercent = 12.0;

        // when
        SimulatorDto.response.graduateSimulation response = new SimulatorDto.response.graduateSimulation(
            categoryDataList, totalCredit, attendedCredit, attendedCreditPercent, leftCreditPercent
        );

        // then
        assertThat(response.getCategoryData()).isEqualTo(categoryDataList);
        assertThat(response.getTotalCredit()).isEqualTo(totalCredit);
        assertThat(response.getAttendedCredit()).isEqualTo(attendedCredit);
        assertThat(response.getAttendedCreditPercent()).isEqualTo(attendedCreditPercent);
        assertThat(response.getLeftCreditPercent()).isEqualTo(leftCreditPercent);
    }
} 