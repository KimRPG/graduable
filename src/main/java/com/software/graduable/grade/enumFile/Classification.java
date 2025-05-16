package com.software.graduable.grade.enumFile;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Optional;

@Getter
public enum Classification {
    FAITH("신앙및세계관"),
    CHARACTER_LEADERSHIP("인성및리더십"),
    PRACTICAL_ENGLISH("실무영어"),
    COMPLEMENTARY_SUBJECT_COURSES("전문교양"),
    BSM("BSM"),
    ICT("ICT융합기초"),
    FREE_ELECTIVES_LIBERAL("자유선택(교양)"),
    FREE_ELECTIVES_LIBERAL_NOT_LIBERAL_ARTS("자유선택(교양또는비교양)"),
    MAJOR("전공주제(AI컴퓨터심화)");


    final private String status;
    Classification(String status) {
        this.status = status;
    }

    @JsonValue
    public String getStatus() {
        return status;
    }
    public static Optional<Classification> nameOf(String name) {
        for (Classification status : Classification.values()) {
            if (status.getStatus().equals(name)) {
                return Optional.of(status);
            }
        }
        return Optional.empty();
    }
}
