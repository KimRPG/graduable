package com.software.graduable.grade;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    @DisplayName("status 맞나 확인")
    void getStatus() {
        assertEquals("교필", Category.LIBERAL_COMPULSORY.getStatus());
        assertEquals("전필", Category.MAJOR_COMPULSORY.getStatus());
    }

    @Test
    @DisplayName("정당한 답이 들어왔을 경우")
    void nameOf() {
        assertEquals(Category.LIBERAL_COMPULSORY, Category.nameOf("교필"));
        assertEquals(Category.MAJOR_ELECTIVE_COMPULSORY, Category.nameOf("전선필"));
    }

    @Test
    @DisplayName("이상한 이름이 들어왔을 경우")
    void values() {
        assertNull(Category.nameOf("22000202"));
        assertNull(Category.nameOf("인성 및 리더쉽"));
        assertNull(Category.nameOf(null));
    }
}