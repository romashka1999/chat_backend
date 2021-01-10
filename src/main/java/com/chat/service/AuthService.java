package com.chat.service;

import com.chat.dto.SignInDto;
import com.chat.dto.SignUpDto;
import com.chat.payload.SignInResponse;
import com.chat.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    @Autowired
    public AuthService(
            AuthenticationManager authenticationManager,
            CustomUserDetailsService customUserDetailsService,
            JwtTokenUtil jwtTokenUtil,
            UserService userService
    ) {
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    public ResponseEntity<?> signIn(SignInDto signInDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInDto.getUsername(), signInDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(signInDto.getUsername());
        String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new SignInResponse(jwt));
    }

    public ResponseEntity<?> signUp(SignUpDto signUpDto) {
        return userService.createUser(signUpDto);
    }
}
