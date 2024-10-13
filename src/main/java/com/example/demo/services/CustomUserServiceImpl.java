package com.example.demo.services;

import com.example.demo.models.CustomUser;
import com.example.demo.repositories.CustomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomUserServiceImpl {

    @Autowired
    private CustomUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public CustomUser getUserByUsername(String username){ return userRepository.findCustomUserByUsername(username); }
    public void registerUser(CustomUser user){
        userRepository.save(CustomUser.builder().username(user.getUsername()).password(passwordEncoder.encode(user.getPassword())).role("USER").build());
    }

}
