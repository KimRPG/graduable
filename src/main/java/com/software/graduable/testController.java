package com.software.graduable;

import com.software.graduable.grade.GradeDTO;
import com.software.graduable.grade.GradeDTOMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class testController {
    private final GradeDTOMapper gradeDTOMapper = new GradeDTOMapper();

    @PostMapping("/hi")
    public List<GradeDTO> parseValidGrades(@RequestBody String text) throws Exception {
        return gradeDTOMapper.parseGradeDTO(text);
    }

}
