package com.chat.service;

import com.chat.dto.SignUpDto;
import com.chat.entity.User;
import com.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void createUser(SignUpDto signUpDto) {
        userRepository.save(new User(signUpDto.getUsername(), signUpDto.getEmail(), signUpDto.getPassword()));
    }
}
