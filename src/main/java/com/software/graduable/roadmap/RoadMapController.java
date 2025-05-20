package com.software.graduable.roadmap;

import com.software.graduable.grade.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class RoadMapController {
    private final RoadMapService roadMapService;
    private final GradeService gradeService;

    @GetMapping("/road-map/{googleId}")
    public List<Map.Entry<String, List<RoadMapSemesterDTO>>> getRoadMap(@PathVariable String googleId) {
        return roadMapService.getAllSemestersWithGradesAndPlannedCourses(googleId);
    }
    @GetMapping("/road-map/now/{googleId}")
    public List<RoadMapSemesterDTO> getNowRoadMap(@PathVariable String googleId) {
        return roadMapService.getNowSemester(googleId);
    }
}
