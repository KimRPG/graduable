package com.software.graduable.plannedCourse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class YearSemesterTest {

    @Test
    @DisplayName("학년도와 학기 생성 테스트")
    void createYearSemester() {
        // given
        int year = 2024;
        int semester = 1;

        // when
        YearSemester yearSemester = new YearSemester(year, semester);

        // then
        assertThat(yearSemester.getYear()).isEqualTo(year);
        assertThat(yearSemester.getSemester()).isEqualTo(semester);
    }

    @Test
    @DisplayName("학년도와 학기 동등성 테스트")
    void yearSemesterEquality() {
        // given
        YearSemester yearSemester1 = new YearSemester(2024, 1);
        YearSemester yearSemester2 = new YearSemester(2024, 1);
        YearSemester yearSemester3 = new YearSemester(2024, 2);

        // when & then
        assertThat(yearSemester1).isEqualTo(yearSemester2);
        assertThat(yearSemester1).isNotEqualTo(yearSemester3);
        assertThat(yearSemester1.hashCode()).isEqualTo(yearSemester2.hashCode());
        assertThat(yearSemester1.hashCode()).isNotEqualTo(yearSemester3.hashCode());
    }

    @Test
    @DisplayName("학년도와 학기 수정 테스트")
    void updateYearSemester() {
        // given
        YearSemester yearSemester = new YearSemester(2024, 1);

        // when
        yearSemester.setYear(2025);
        yearSemester.setSemester(2);

        // then
        assertThat(yearSemester.getYear()).isEqualTo(2025);
        assertThat(yearSemester.getSemester()).isEqualTo(2);
    }
} 