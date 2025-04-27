package com.software.graduable.grade;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;


@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Grade {
    A_PLUS("A+", 4.5),
    A("A0", 4.0),
    B_PLUS("B+", 3.5),
    B("B0", 3.0),
    C_PLUS("C+", 2.5),
    C("C0", 2.0),
    D_PLUS("D+", 1.5),
    D("D0", 1.0),
    F("F", 0),
    P("P", 0),
    ;

    final private String status;
    final private double point;
    Grade(String status, double point) {
        this.status = status;
        this.point = point;
    }
    public static Grade nameOf(String name) {
        for (Grade status : Grade.values()) {
            if (status.getStatus().equals(name)) {
                return status;
            }
        }
        return null;
    }
}
