package com.software.graduable.grade;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
public enum Credit {
    A_PLUS("A+"),
    A("A"),
    B_PLUS("B+"),
    B("B"),
    C_PLUS("C+"),
    C("C"),
    D_PLUS("D+"),
    D("D"),
    F("F");

    final private String status;
    Credit(String status) {
        this.status = status;
    }
    public static Credit nameOf(String name) {
        for (Credit status : Credit.values()) {
            if (status.getStatus().equals(name)) {
                return status;
            }
        }
        return null;
    }
}
