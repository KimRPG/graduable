package com.software.graduable.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    @DisplayName("사용자 생성 테스트")
    void createUser() {
        // given
        String userName = "홍길동";
        String userNickname = "길동이";
        int userSemester = 3;
        String googleId = "google123";
        String email = "test@example.com";
        Long yearOfSemester = 2024L;
        Long semesterInYear = 1L;

        // when
        User user = User.builder()
                .userName(userName)
                .userNickname(userNickname)
                .userSemester(userSemester)
                .googleId(googleId)
                .email(email)
                .yearOfSemester(yearOfSemester)
                .semesterInYear(semesterInYear)
                .build();

        // then
        assertThat(user.getUserName()).isEqualTo(userName);
        assertThat(user.getUserNickname()).isEqualTo(userNickname);
        assertThat(user.getUserSemester()).isEqualTo(userSemester);
        assertThat(user.getGoogleId()).isEqualTo(googleId);
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getYearOfSemester()).isEqualTo(yearOfSemester);
        assertThat(user.getSemesterInYear()).isEqualTo(semesterInYear);
    }

} 