package com.software.graduable.user;

import com.software.graduable.grade.GradeJPA;
import com.software.graduable.plannedCourse.PlannedCourseJPA;
import com.software.graduable.user.dto.UserDTO;
import com.software.graduable.user.dto.UserInfoDTO;
import com.software.graduable.global.exception.exceptions.UserAlreadyExistsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserJPA userJPA;

    @Mock
    private GradeJPA gradeJPA;

    @Mock
    private PlannedCourseJPA plannedCourseJPA;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("사용자 회원가입 테스트")
    void userSignUp() {
        // given
        UserDTO userDTO = UserDTO.builder()
                .googleId("google123")
                .email("test@example.com")
                .userName("홍길동")
                .userNickname("길동이")
                .userSemester(3)
                .yearOfSemester(2024L)
                .semesterInYear(1L)
                .build();

        when(userJPA.findByGoogleId(any())).thenReturn(null);
        when(userJPA.save(any())).thenReturn(new User().toEntity(userDTO));

        // when
        userService.userSignUp(userDTO);

        // then
        verify(userJPA, times(1)).save(any());
    }

    @Test
    @DisplayName("이미 존재하는 사용자 회원가입 시도 테스트")
    void userSignUpWithExistingUser() {
        // given
        UserDTO userDTO = UserDTO.builder()
                .googleId("google123")
                .email("test@example.com")
                .build();

        when(userJPA.findByGoogleId(any())).thenReturn(new User());

        // when & then
        assertThatThrownBy(() -> userService.userSignUp(userDTO))
                .isInstanceOf(UserAlreadyExistsException.class);
    }

    @Test
    @DisplayName("사용자 정보 조회 테스트")
    void getUserInfo() {
        // given
        String googleId = "google123";
        User user = User.builder()
                .googleId(googleId)
                .email("test@example.com")
                .userName("홍길동")
                .build();

        when(userJPA.findByGoogleId(googleId)).thenReturn(user);

        // when
        UserInfoDTO result = userService.getUserInfo(googleId);

        // then
        assertThat(result.getGoogleId()).isEqualTo(googleId);
        assertThat(result.getEmail()).isEqualTo("test@example.com");
        assertThat(result.getUserName()).isEqualTo("홍길동");
    }

    @Test
    @DisplayName("모든 사용자 정보 조회 테스트")
    void getUsersInfo() {
        // given
        User user1 = User.builder()
                .googleId("google1")
                .email("test1@example.com")
                .userName("홍길동")
                .build();

        User user2 = User.builder()
                .googleId("google2")
                .email("test2@example.com")
                .userName("김철수")
                .build();

        when(userJPA.findAll()).thenReturn(Arrays.asList(user1, user2));

        // when
        List<UserInfoDTO> results = userService.getUsersInfo();

        // then
        assertThat(results).hasSize(2);
        assertThat(results.get(0).getGoogleId()).isEqualTo("google1");
        assertThat(results.get(1).getGoogleId()).isEqualTo("google2");
    }
} 