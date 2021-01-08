package com.chat.service;

import com.chat.dto.SignUpDto;
import com.chat.entity.User;
import com.chat.payload.SignUpResponse;
import com.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<?> createUser(SignUpDto signUpDto) {
        if(userRepository.existsByUsername(signUpDto.getUsername())) {
            return ResponseEntity
                        .badRequest()
                        .body(new SignUpResponse("Error: Username already exists"));
        }

        if(userRepository.existsByEmail(signUpDto.getEmail())) {
            return ResponseEntity
                        .badRequest()
                        .body(new SignUpResponse("Error: Email already exists"));
        }
        var encodedPassword = encoder.encode(signUpDto.getPassword());
        System.out.println(encodedPassword);
        User user = new User(signUpDto.getUsername(), signUpDto.getEmail(), encodedPassword);

        userRepository.save(user);
        return ResponseEntity
                    .ok(new SignUpResponse("User successfully registered"));
    }
}
