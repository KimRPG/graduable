package com.software.graduable.user.dto;

import com.software.graduable.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDTO {
    private String userName;    // 유저 이름
    private String userNickname;    // 유저 닉네임
    private int userSemester;    // 유저 현 학기 수
    private String googleId;
    private String email;
    private Long yearOfSemester; // 현 학기가 몇년도인지 ex) 2025
    private Long semesterInYear; // 현 학기가 1학기인지 2학기인지 ex) 1

    public UserDTO toDto(User user) {
        return UserDTO.builder()
                .userName(user.getUserName())
                .userNickname(user.getUserNickname())
                .userSemester(user.getUserSemester())
                .googleId(user.getGoogleId())
                .email(user.getEmail())
                .yearOfSemester(user.getYearOfSemester())
                .semesterInYear(user.getSemesterInYear())
                .build();
    }
}
