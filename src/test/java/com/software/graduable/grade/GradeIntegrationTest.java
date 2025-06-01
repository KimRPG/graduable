package com.software.graduable.grade;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.software.graduable.config.IntegrationTestConfig;
import com.software.graduable.grade.dto.GradeDTO;
import com.software.graduable.grade.dto.GradeOutputDTO;
import com.software.graduable.grade.enumFile.Category;
import com.software.graduable.grade.enumFile.Classification;
import com.software.graduable.grade.enumFile.Grade;
import com.software.graduable.user.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
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
class GradeIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String googleId;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() throws Exception {
        // 테스트용 사용자 생성
        googleId = "testGoogleId123";
        userDTO = UserDTO.builder()
                .userName("테스트유저")
                .userNickname("테스트닉네임")
                .userSemester(3)
                .googleId(googleId)
                .email("test@example.com")
                .yearOfSemester(2024L)
                .semesterInYear(1L)
                .build();

        // 기존 사용자 삭제
        mockMvc.perform(delete("/user/" + googleId))
                .andExpect(status().isOk());

        // 사용자 생성
        mockMvc.perform(post("/user/sign_up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isOk());

        // 기존 성적 데이터 삭제
        mockMvc.perform(delete("/grade/" + googleId))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("성적 등록 통합 테스트")
    void addGradeTest() throws Exception {
        // given
        String gradeText = " 인성및리더십\n" +
                "구분\t연도\t학기\t과목코드\t과목명\t학점(설계)\t성적\t비고\n" +
                "교필\t2020\t1\tGEK10008\t 공동체리더십훈련1\t0.5\tP\t \n" +
                "교필\t2020\t2\tGEK10009\t 공동체리더십훈련2\t0.5\tP\t \n" +
                "교필\t2020\t2\tGEK10015\t 한동인성교육\t1\tP\t \n" +
                "교필\t2022\t2\tGEK20008\t 공동체리더십훈련3\t0.5\tP\t \n" +
                "교필\t2023\t1\tGEK20009\t 공동체리더십훈련4\t0.5\tP\t \n" +
                "교필\t2023\t2\tGEK30008\t 공동체리더십훈련5\t0.5\tP\t \n" +
                "교필\t2024\t1\tGEK30009\t 공동체리더십훈련6\t0.5\tP\t ";

        // when & then
        mockMvc.perform(post("/grade/" + googleId)
                .contentType(MediaType.TEXT_PLAIN)
                .content(gradeText))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("전체 성적 조회 통합 테스트")
    void getAllGradesTest() throws Exception {
        // given
        String gradeText = " 인성및리더십\n" +
                "구분\t연도\t학기\t과목코드\t과목명\t학점(설계)\t성적\t비고\n" +
                "교필\t2020\t1\tGEK10008\t 공동체리더십훈련1\t0.5\tP\t \n" +
                "교필\t2020\t2\tGEK10009\t 공동체리더십훈련2\t0.5\tP\t \n" +
                "교필\t2020\t2\tGEK10015\t 한동인성교육\t1\tP\t \n" +
                "교필\t2022\t2\tGEK20008\t 공동체리더십훈련3\t0.5\tP\t \n" +
                "교필\t2023\t1\tGEK20009\t 공동체리더십훈련4\t0.5\tP\t \n" +
                "교필\t2023\t2\tGEK30008\t 공동체리더십훈련5\t0.5\tP\t \n" +
                "교필\t2024\t1\tGEK30009\t 공동체리더십훈련6\t0.5\tP\t ";
        
        mockMvc.perform(post("/grade/" + googleId)
                .contentType(MediaType.TEXT_PLAIN)
                .content(gradeText))
                .andExpect(status().isOk());

        // when & then
        mockMvc.perform(get("/grade/" + googleId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].courseName").value("공동체리더십훈련1"))
                .andExpect(jsonPath("$[1].courseName").value("공동체리더십훈련2"))
                .andExpect(jsonPath("$[2].courseName").value("한동인성교육"));
    }

    @Test
    @DisplayName("성적 삭제 통합 테스트")
    void deleteAllGradesTest() throws Exception {
        // given
        String gradeText = " 인성및리더십\n" +
                "구분\t연도\t학기\t과목코드\t과목명\t학점(설계)\t성적\t비고\n" +
                "교필\t2020\t1\tGEK10008\t 공동체리더십훈련1\t0.5\tP\t \n" +
                "교필\t2020\t2\tGEK10009\t 공동체리더십훈련2\t0.5\tP\t \n" +
                "교필\t2020\t2\tGEK10015\t 한동인성교육\t1\tP\t \n" +
                "교필\t2022\t2\tGEK20008\t 공동체리더십훈련3\t0.5\tP\t \n" +
                "교필\t2023\t1\tGEK20009\t 공동체리더십훈련4\t0.5\tP\t \n" +
                "교필\t2023\t2\tGEK30008\t 공동체리더십훈련5\t0.5\tP\t \n" +
                "교필\t2024\t1\tGEK30009\t 공동체리더십훈련6\t0.5\tP\t ";
        
        mockMvc.perform(post("/grade/" + googleId)
                .contentType(MediaType.TEXT_PLAIN)
                .content(gradeText))
                .andExpect(status().isOk());

        // when & then
        mockMvc.perform(delete("/grade/" + googleId))
                .andExpect(status().isOk());

        // 삭제 후 조회 시 빈 리스트 반환 확인
        mockMvc.perform(get("/grade/" + googleId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }
} 