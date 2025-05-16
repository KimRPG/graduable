package com.software.graduable.grade;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;


class GradeDTOMapperTest {

        private final GradeDTOMapper mapper = new GradeDTOMapper();

        @Test
        @DisplayName("parse_올바른 입력이라면 GradeDTO 리스트를 리턴한다")
        void parseCorrectTest() throws Exception {
            String input =
                    """
                             자유선택(교양)
                            교선필\t2020\t2\tGEK10075\t 1학년 세미나\t1\tP\t\s
                            교선\t2020\t2\tCUE10004\t 공간학입\t3\tA+""";

            List<GradeDTO> result = mapper.parseGradeDTO(input);

            assertThat(result).hasSize(2);
            assertThat(result.get(0).getCourseName()).isEqualTo("1학년 세미나");
            assertThat(result.get(1).getCredit()).isEqualTo(3);
        }

        @Test
        @DisplayName("형식이_잘못된_줄은_무시된다")
        void parseWrongLine() throws Exception {
            String input = " 자유선택(교양)\n"+
                    "교선필\t2023\t2\tCSE101\t프로그래밍기초\t3(3)\tA+\n" +
                    "잘못된줄";

            List<GradeDTO> result = mapper.parseGradeDTO(input);
            assertThat(result).hasSize(1);
            assertThat(result.get(0).getClassification()).isEqualTo(Classification.FREE_ELECTIVES_LIBERAL);
        }

        @Test
        @DisplayName("enum_매칭에_실패하면_그_줄은_무시된다")
        void parseEnumMatching() throws Exception {
            String input = "자유선택(교양)\n" +
                    "BSM\n" +
                    "헛소리\t2023\t2\tCSE101\t프로그래밍기초\t3(3)\tA+\n" +
                    "전선\t2023\t2\tCSE101\t프로그래밍기초\t3(3)\tA+";

            List<GradeDTO> result = mapper.parseGradeDTO(input);
            assertThat(result).hasSize(1);
            assertThat(result.get(0).getCategory()).isEqualTo(Category.MAJOR_ELECTIVE);
            assertThat(result.get(0).getClassification()).isEqualTo(Classification.BSM);
        }

        @Test
        @DisplayName("subjectNote가_없으면_빈문자열이_된다")
        void parseNoSubjectNote() throws Exception {
            String input = "전선\t2023\t2\tCSE101\t프로그래밍기초\t3(3)\tA+";
            List<GradeDTO> result = mapper.parseGradeDTO(input);
            assertThat(result.get(0).getSubjectNote()).isEqualTo("");
            assertThat(result.get(0).getClassification()).isNull();
        }

        @Test
        @DisplayName("subjectNote가 있으면 subjectNote가 추가된다")
        void parseYesSubjectNote() throws Exception {
            String input = "전선필\t2023\t2\tCSE101\t프로그래밍기초\t3(3)\tA+\t비고사항";
            List<GradeDTO> result = mapper.parseGradeDTO(input);
            assertThat(result.get(0).getSubjectNote()).isEqualTo("비고사항");
        }

}
