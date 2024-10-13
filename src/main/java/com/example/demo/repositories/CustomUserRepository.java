package com.example.demo.repositories;

import com.example.demo.models.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomUserRepository extends JpaRepository<CustomUser, Long> {
    CustomUser findCustomUserByUsername(String username);
    CustomUser save(CustomUser customUser);

}

