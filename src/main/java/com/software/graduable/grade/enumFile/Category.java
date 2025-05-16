package com.software.graduable.grade.enumFile;


import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

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

    @JsonValue
    public String getStatus() {
        return status;
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
