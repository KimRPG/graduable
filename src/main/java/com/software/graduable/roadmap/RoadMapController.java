package com.software.graduable.roadmap;

import com.software.graduable.grade.service.GradeService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class RoadMapController {
    private final RoadMapService roadMapService;
    private final GradeService gradeService;

    @GetMapping("/road-map/{googleId}")
    public List<Map.Entry<Long, List<RoadMapSemesterDTO>>> getRoadMap(@PathVariable String googleId) {
        return roadMapService.getAllSemestersWithGradesAndPlannedCourses(googleId);
    }

    @PatchMapping("/road-map/{googleId}")
    public ResponseEntity<?> patchRoadMap(@PathVariable String googleId, @RequestBody RoadMapDTO.saveToRoadmap request) {
        return roadMapService.patchRoadMapSemester(googleId, request);
    }


    @GetMapping("/road-map/now/{googleId}")
    public List<RoadMapSemesterDTO> getNowRoadMap(@PathVariable String googleId) {
        return roadMapService.getNowSemester(googleId);
    }

    @DeleteMapping ("/road-map/{googleId}")
    public void deleteRoadMap(@PathVariable String googleId, @RequestParam Long semester) {
        roadMapService.deleteUserAndSemester(googleId, semester - 1);
    }


}
