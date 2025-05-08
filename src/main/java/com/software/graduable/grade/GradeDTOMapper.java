package com.software.graduable.grade;

import java.io.*;
import java.util.*;

public class GradeDTOMapper {

    public List<GradeDTO> parse(String text) throws Exception {
        List<GradeDTO> gradeList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new StringReader(text));
        String line;

        while ((line = reader.readLine()) != null) {
            Optional<GradeDTO> maybeDto = parseLine(line);
            maybeDto.ifPresent(gradeList::add);
        }

        return gradeList;
    }

    private Optional<GradeDTO> parseLine(String line) {
        String[] tokens = line.split("\t");
        if (tokens.length < 7) return Optional.empty();

        Category category = Category.nameOf(tokens[0].trim());
        Grade grade = Grade.nameOf(tokens[6].trim());

        if (category == null || grade == null) return Optional.empty();

        int credit = parseInt(tokens[5]);

        return Optional.of(
                GradeDTO.builder()
                        .classification(category)
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

}
