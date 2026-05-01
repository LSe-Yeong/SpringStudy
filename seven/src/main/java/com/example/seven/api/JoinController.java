package com.example.seven.api;

import com.example.seven.domain.user.dto.UserRequestDto;
import com.example.seven.domain.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JoinController {

    private final UserService userService;

    public JoinController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/join")
    public String join(UserRequestDto dto) {
        userService.join(dto);

        return "redirect:/";
    }

    @GetMapping("/join")
    public String joinPage() {
        return "join";
    }
}
