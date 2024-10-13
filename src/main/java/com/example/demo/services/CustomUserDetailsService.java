package com.example.demo.services;

import com.example.demo.models.CustomUser;
import com.example.demo.repositories.CustomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomUserServiceImpl customUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUser userDb = customUserService.getUserByUsername(username);
        return (userDb == null ) ? null : User.builder().username(userDb.getUsername()).password(userDb.getPassword()).roles(userDb.getRole()).build() ;
    }
}
