package com.software.graduable.grade.service;

import com.software.graduable.grade.dto.GradeDTO;
import com.software.graduable.grade.enumFile.Category;
import com.software.graduable.grade.enumFile.Classification;
import com.software.graduable.grade.enumFile.Grade;

import java.io.*;
import java.util.*;

public class GradeDTOMapper {


    public List<GradeDTO> parseGradeDTO(String text) throws Exception {
        List<GradeDTO> gradeList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new StringReader(text));
        String line;
        Classification classification = null;
        while ((line = reader.readLine()) != null) {
            line = line.strip();
            Optional<Classification> maybeClassification = Classification.nameOf(line);
            if (maybeClassification.isPresent()) {
                classification = maybeClassification.get();
                continue; // 다음 줄로 넘어감
            }
            Optional<GradeDTO> maybeDto = parseLine(line,classification);
            maybeDto.ifPresent(gradeList::add);
        }

        return gradeList;
    }

    private Optional<GradeDTO> parseLine(String line, Classification classification) {
        String[] tokens = line.split("\t");
        if (tokens.length < 7) return Optional.empty();

        Category category = Category.nameOf(tokens[0].trim());
        Grade grade = Grade.nameOf(tokens[6].trim());

        if (category == null || grade == null) return Optional.empty();

        double credit = parseDouble(tokens[5]);

        return Optional.of(
                GradeDTO.builder()
                        .classification(classification)
                        .category(category)
                        .yearCourseTaken(parseInt(tokens[1]))
                        .semesterCourseTaken(parseInt(tokens[2]))
                        .courseCode(tokens[3].trim())
                        .courseName(tokens[4].trim())
                        .credit(credit)
                        .grade(grade)
                        .subjectNote(tokens.length >= 8 ? tokens[7].trim() : "")
                        .build()
        );
    }
    private static int parseInt(String raw) {
        String numOnly = raw.trim().split("\\(")[0];
        return parseIntSafe(numOnly);
    }

    private static int parseIntSafe(String str) {
        try {
            return Integer.parseInt(str.trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private static double parseDouble(String raw) {
        String numOnly = raw.trim().split("\\(")[0];
        return parseDoubleSafe(numOnly);
    }

    private static double parseDoubleSafe(String str) {
        try {
            return Double.parseDouble(str.trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

}
