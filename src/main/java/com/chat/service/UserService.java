package com.chat.service;

import com.chat.dto.SignUpDto;
import com.chat.entity.User;
import com.chat.payload.SignUpResponse;
import com.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

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
        var encodedPassword = passwordEncoder.encode(signUpDto.getPassword());
        System.out.println(encodedPassword);
        User user = new User(signUpDto.getUsername(), signUpDto.getEmail(), encodedPassword);

        userRepository.save(user);
        return ResponseEntity.ok(new SignUpResponse("User successfully registered"));
    }

    public ResponseEntity<?> getMyProfileData(Authentication authentication) throws Exception {
        var username = authentication.getName();
        var user = userRepository.findByUsername(username);
        if(user.isEmpty()) {
            throw new Exception("USER_NOT_FOUND");
        }
        return ResponseEntity.ok(user);
    }
}
