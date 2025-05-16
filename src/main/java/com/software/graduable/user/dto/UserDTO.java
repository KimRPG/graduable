package com.software.graduable.user.dto;

import com.software.graduable.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDTO {
    private String userName;    // 유저 이름
    private String userNickname;    // 유저 닉네임
    private String userSemester;    // 유저 현 학기 수
    private String googleId;
    private String email;

    public UserDTO toDto(User user) {
        return UserDTO.builder()
                .userName(user.getUserName())
                .userName(user.getUserNickname())
                .userSemester(user.getUserSemester())
                .googleId(user.getGoogleId())
                .email(user.getEmail())
                .build();
    }
}
