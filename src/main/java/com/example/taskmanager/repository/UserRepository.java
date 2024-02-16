package com.example.taskmanager.repository;


import com.example.taskmanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Add custom query methods if needed
    Optional<User> findByUsername(String username);
    Optional<User> findFirstByUsername(String username);
}