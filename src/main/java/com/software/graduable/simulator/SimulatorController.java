package com.software.graduable.simulator;

//import io.swagger.v3.oas.annotations.Operation;
import com.software.graduable.course.CourseJPA;
import com.software.graduable.grade.GradeJPA;
import com.software.graduable.user.UserJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/simulator")
public class SimulatorController {
    private final SimulatorService simulatorService;
    private final UserJPA userJPA;
    private final CourseJPA courseJPA;
    private final GradeJPA gradeJPA;

    @GetMapping("/search")
//    @Operation(summary = "과목 정보 조회하기 기능", description = "name으로 과목 이름을 보내주면 해당 과목의 정보를 받습니다.")
    public ResponseEntity<?> findSubjectByName(@RequestParam String name) {
        SimulatorDto.response.search response = simulatorService.findSubjectByName(name);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/graduateSimulation")
    public ResponseEntity<?> graduateSimulation(@RequestBody SimulatorDto.request.graduateSimulation request){
        SimulatorDto.response.graduateSimulation response = simulatorService.graduateSimulation(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/saveToRoadmap")
    public ResponseEntity<?> saveToRoadmap(@RequestBody SimulatorDto.request.saveToRoadmap request){
        simulatorService.saveToRoadmap(request);
        return ResponseEntity.ok(true);
    }
}
