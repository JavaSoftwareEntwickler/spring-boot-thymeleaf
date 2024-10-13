package com.example.demo.services;

import com.example.demo.models.CustomUser;
import com.example.demo.models.UserRole;
import com.example.demo.repositories.CustomUserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
        userRepository.save(CustomUser.builder().username(user.getUsername()).password(passwordEncoder.encode(user.getPassword())).role(UserRole.USER).build());
    }
    @PostConstruct
    public void postConstruct(){
        try{
            userRepository.save(CustomUser.builder()
                    .username("user@user.com")
                    .password(passwordEncoder.encode("user"))
                    .role(UserRole.USER)
                    .build());
        userRepository.save(CustomUser.builder()
                        .username("admin@admin.com")
                        .password(passwordEncoder.encode("admin"))
                        .role(UserRole.ADMIN)
                .build());
        userRepository.save(CustomUser.builder()
                .username("super@super.com")
                .password(passwordEncoder.encode("super"))
                .role(UserRole.SUPER_USER)
                .build());
        }catch (DataIntegrityViolationException e){

        }
    }

}
