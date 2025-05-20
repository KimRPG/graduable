package com.software.graduable.plannedCourse;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class YearSemester {
    public int year;
    int semester;

    public YearSemester(int year, int semester) {
        this.year = year;
        this.semester = semester;
    }

    // equals & hashCode 구현 필수!
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof YearSemester)) return false;
        YearSemester ys = (YearSemester) o;
        return year == ys.year && semester == ys.semester;
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, semester);
    }
}