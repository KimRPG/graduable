package com.software.graduable.roadmap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class RoadMapDTOTest {

    @Test
    @DisplayName("로드맵 DTO 생성 테스트")
    void createRoadMapDTO() {
        // given
        String yearSemester = "2024-1";
        List<RoadMapSemesterDTO> semesterDTOs = Arrays.asList(
            RoadMapSemesterDTO.builder()
                .courseName("자바 프로그래밍")
                .credit(3.0)
                .category(true)
                .semester(1)
                .courseID(1L)
                .build(),
            RoadMapSemesterDTO.builder()
                .courseName("데이터베이스")
                .credit(3.0)
                .category(true)
                .semester(1)
                .courseID(2L)
                .build()
        );
        Map<String, List<RoadMapSemesterDTO>> map = new HashMap<>();
        map.put(yearSemester, semesterDTOs);

        // when
        RoadMapDTO roadMapDTO = new RoadMapDTO(map, yearSemester);

        // then
        assertThat(roadMapDTO.getYearSemester()).isEqualTo(yearSemester);
        assertThat(roadMapDTO.getRoadMapSemesterDTO()).isEqualTo(semesterDTOs);
    }

    @Test
    @DisplayName("로드맵 저장 DTO 생성 테스트")
    void createSaveToRoadmapDTO() {
        // given
        List<Long> courseIdList = Arrays.asList(1L, 2L, 3L);
        Long semester = 1L;

        // when
        RoadMapDTO.saveToRoadmap saveToRoadmap = new RoadMapDTO.saveToRoadmap();
        saveToRoadmap.setCourseIdList(courseIdList);
        saveToRoadmap.setSemester(semester);

        // then
        assertThat(saveToRoadmap.getCourseIdList()).isEqualTo(courseIdList);
        assertThat(saveToRoadmap.getSemester()).isEqualTo(semester);
    }
} 