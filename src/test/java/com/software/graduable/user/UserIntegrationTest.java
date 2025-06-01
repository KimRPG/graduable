package com.software.graduable.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.software.graduable.config.IntegrationTestConfig;
import com.software.graduable.user.dto.UserDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = IntegrationTestConfig.class)
class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("사용자 생성 통합 테스트")
    void createUserTest() throws Exception {
        // given
        UserDTO requestDto = UserDTO.builder()
                .userName("홍길동")
                .userNickname("길동이")
                .userSemester(3)
                .googleId("google123")
                .email("test@example.com")
                .yearOfSemester(2024L)
                .semesterInYear(1L)
                .build();

        // when
        mockMvc.perform(post("/user/sign_up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        // then
        mockMvc.perform(get("/user/info/" + requestDto.getGoogleId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value(requestDto.getUserName()))
                .andExpect(jsonPath("$.userNickname").value(requestDto.getUserNickname()))
                .andExpect(jsonPath("$.userSemester").value(requestDto.getUserSemester()))
                .andExpect(jsonPath("$.googleId").value(requestDto.getGoogleId()))
                .andExpect(jsonPath("$.email").value(requestDto.getEmail()))
                .andExpect(jsonPath("$.yearOfSemester").value(requestDto.getYearOfSemester()))
                .andExpect(jsonPath("$.semesterInYear").value(requestDto.getSemesterInYear()));
    }

    @Test
    @DisplayName("사용자 정보 조회 통합 테스트")
    void getUserTest() throws Exception {
        // given
        UserDTO createDto = UserDTO.builder()
                .userName("김철수")
                .userNickname("철수야")
                .userSemester(2)
                .googleId("google456")
                .email("test2@example.com")
                .yearOfSemester(2024L)
                .semesterInYear(1L)
                .build();

        // 사용자 생성
        mockMvc.perform(post("/user/sign_up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createDto)))
                .andExpect(status().isOk());

        // when & then
        mockMvc.perform(get("/user/info/" + createDto.getGoogleId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value(createDto.getUserName()))
                .andExpect(jsonPath("$.userNickname").value(createDto.getUserNickname()))
                .andExpect(jsonPath("$.userSemester").value(createDto.getUserSemester()))
                .andExpect(jsonPath("$.googleId").value(createDto.getGoogleId()))
                .andExpect(jsonPath("$.email").value(createDto.getEmail()))
                .andExpect(jsonPath("$.yearOfSemester").value(createDto.getYearOfSemester()))
                .andExpect(jsonPath("$.semesterInYear").value(createDto.getSemesterInYear()));
    }

    @Test
    @DisplayName("사용자 정보 수정 통합 테스트")
    void updateUserTest() throws Exception {
        // given
        UserDTO createDto = UserDTO.builder()
                .userName("이영희")
                .userNickname("영희야")
                .userSemester(4)
                .googleId("google789")
                .email("test3@example.com")
                .yearOfSemester(2024L)
                .semesterInYear(1L)
                .build();

        // 사용자 생성
        mockMvc.perform(post("/user/sign_up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createDto)))
                .andExpect(status().isOk());

        // 수정할 정보
        Integer newSemester = 5;

        // when & then
        mockMvc.perform(put("/user/info/" + createDto.getGoogleId())
                .param("semester", newSemester.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value(createDto.getUserName()))
                .andExpect(jsonPath("$.userNickname").value(createDto.getUserNickname()))
                .andExpect(jsonPath("$.userSemester").value(newSemester))
                .andExpect(jsonPath("$.googleId").value(createDto.getGoogleId()))
                .andExpect(jsonPath("$.email").value(createDto.getEmail()))
                .andExpect(jsonPath("$.yearOfSemester").value(createDto.getYearOfSemester()))
                .andExpect(jsonPath("$.semesterInYear").value(createDto.getSemesterInYear()));
    }
} 