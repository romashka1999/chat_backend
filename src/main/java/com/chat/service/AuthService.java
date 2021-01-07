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
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    UserService userService;

    public ResponseEntity<?> signIn(SignInDto signInDto) throws Exception{
        try {
            System.out.println("tryshiaaaaaaaaaaaaaaaaaaaaaa");
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signInDto.getUsername(), signInDto.getPassword())
            );
        } catch (BadCredentialsException e) {
            System.out.println("aqacaaaaaaaaaaaaaaaaaaa");
            throw new Exception("Incorrect username or password", e);
        }
        System.out.println("kibatonooooo");
        final var userDetails = customUserDetailsService
                .loadUserByUsername(signInDto.getUsername());
        System.out.println(userDetails);
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        System.out.println(jwt);
        return ResponseEntity.ok(new SignInResponse(jwt));
    }

    public ResponseEntity<?> signUp(SignUpDto signUpDto) {
        userService.createUser(signUpDto);
        return ResponseEntity.ok(new SignInResponse("daemataaa"));
    }
}
