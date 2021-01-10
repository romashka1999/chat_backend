package com.chat.controller;

import com.chat.dto.SignInDto;
import com.chat.dto.SignUpDto;
import com.chat.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("signin")
    public ResponseEntity<?> signIn(@Valid @RequestBody SignInDto signInDto) throws Exception {
        return authService.signIn(signInDto);
    }

    @PostMapping("signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpDto signUpDto) throws Exception {
        return authService.signUp(signUpDto);
    }

}
