package com.software.graduable.user;

import com.software.graduable.user.dto.UserDTO;
import com.software.graduable.user.dto.UserInfoDTO;
import com.software.graduable.global.exception.exceptions.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserJPA jpa;

    public void userSignUp(UserDTO userDTO) {
        // 필수 데이터 검증
        if (userDTO.getGoogleId() == null || userDTO.getEmail() == null) {
            throw new InvalidUserDataException("Google ID와 이메일은 필수 입력값입니다.");
        }

        // 이미 존재하는 사용자인지 확인
        if (jpa.findByGoogleId(userDTO.getGoogleId()) != null) {
            throw new UserAlreadyExistsException("이미 가입된 사용자입니다: " + userDTO.getGoogleId());
        }

        jpa.save(new User().toEntity(userDTO));
    }

    public UserInfoDTO getUserInfo(String googleId) {
        User user = jpa.findByGoogleId(googleId);
        if (user == null) {
            throw new UserNotFoundException("사용자를 찾을 수 없습니다: " + googleId);
        }
        return new UserInfoDTO().toDto(user);
    }

    public List<UserInfoDTO> getUsersInfo() {
        return jpa.findAll().stream().map(UserInfoDTO::toDto).toList();
    }

    public UserInfoDTO putUserSemesterChange(String googleId, int semester) {
        jpa.updateUserSemester(googleId, semester);
        User user = jpa.findByGoogleId(googleId);
        if (user == null) {
            throw new UserNotFoundException("사용자를 찾을 수 없습니다: " + googleId);
        }
        return getUserInfo(googleId);
    }

    public void deleteUser(String googleId) {
        jpa.deleteByGoogleId(googleId);
    }
}
