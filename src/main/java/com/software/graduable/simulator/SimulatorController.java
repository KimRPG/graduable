package com.software.graduable.simulator;

//import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/simulator")
public class SimulatorController {
    private final SimulatorService simulatorService;

    @GetMapping("/search")
//    @Operation(summary = "과목 정보 조회하기 기능", description = "name으로 과목 이름을 보내주면 해당 과목의 정보를 받습니다.")
    public ResponseEntity<SimulatorDto.response.search> findSubjectByName(@RequestParam String name) {
        SimulatorDto.response.search response = simulatorService.findSubjectByName(name);
        return ResponseEntity.ok(response);
    }
}
