package com.software.graduable.grade;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum Category {
    LIBERAL_COMPULSORY("교필"),
    LIBERAL_ELECTIVE_COMPULSORY("교선필"),
    LIBERAL_ELECTIVE("교선"),
    MAJOR_ELECTIVE("전선"),
    MAJOR_COMPULSORY("전필"),
    MAJOR_ELECTIVE_COMPULSORY("전선필");

    final private String status;
    Category(String status) {
        this.status = status;
    }

    public static Category nameOf(String name) {
        for (Category status : Category.values()) {
            if (status.getStatus().equals(name)) {
                return status;
            }
        }
        return null;
    }
}
