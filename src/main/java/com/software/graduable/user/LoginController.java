package com.software.graduable.user;

import com.software.graduable.grade.service.GradeService;
import com.software.graduable.user.dto.UserDTO;
import com.software.graduable.user.dto.UserInfoDTO;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final UserService service;
    private final GradeService gradeService;

    @PostMapping("/user/sign_up")
    public void signUp(@RequestBody UserDTO dto)  {
        service.userSignUp(dto);
    }

    @GetMapping("/user/info/{googleId}")
    public UserInfoDTO info(@PathVariable("googleId") String id) {
        return service.getUserInfo(id);
    }
}
