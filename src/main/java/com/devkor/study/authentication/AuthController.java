package com.devkor.study.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    @PostMapping("/login")
    public JwtInfoDto login(@RequestBody AuthDto authDto){
        return authService.login(authDto.getUserId(), authDto.getPassword());
    }
}
