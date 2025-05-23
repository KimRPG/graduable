package com.software.graduable.roadmap;

import com.software.graduable.grade.service.GradeService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
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
    public List<Map.Entry<String, List<RoadMapSemesterDTO>>> getRoadMap(@PathVariable String googleId) {
        return roadMapService.getAllSemestersWithGradesAndPlannedCourses(googleId);
    }
    @GetMapping("/road-map/now/{googleId}")
    public List<RoadMapSemesterDTO> getNowRoadMap(@PathVariable String googleId) {
        return roadMapService.getNowSemester(googleId);
    }

    @DeleteMapping ("/road-map/{googleId}")
    public boolean deleteRoadMap(@PathVariable String googleId, @RequestParam Long semester) {
        return roadMapService.deleteUserAndSemester(googleId, semester);
    }

//    @GetMapping("/view-pdf")
//    public void viewPdf(HttpServletResponse response) throws IOException {
//        // PDF 파일 경로 또는 바이너리 데이터
//        Path pdfPath = Paths.get("src/main/java/com/software/graduable/roadmap/practice.pdf");
//
//        // 응답 설정
//        response.setContentType("application/pdf");
//        response.setHeader("Content-Disposition", "inline; filename=example.pdf");
//
//        // PDF 파일을 스트림으로 복사
//        Files.copy(pdfPath, response.getOutputStream());
//        response.getOutputStream().flush();
//    }

}
