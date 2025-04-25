package com.software.graduable.simulator;

//import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/simulator")
public class SimulatorController {
    private final SimulatorService simulatorService;

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
}
