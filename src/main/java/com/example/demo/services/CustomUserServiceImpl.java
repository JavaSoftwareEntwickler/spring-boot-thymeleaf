package com.example.demo.services;

import com.example.demo.models.CustomUser;
import com.example.demo.repositories.CustomUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class CustomUserServiceImpl {

    private final CustomUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomUser getUserByUsername(String username){ return userRepository.findCustomUserByUsername(username); }
    public void registerUser(CustomUser user){
        userRepository.save(CustomUser.builder().username(user.getUsername()).password(passwordEncoder.encode(user.getPassword())).role("USER").build());
    }

}
