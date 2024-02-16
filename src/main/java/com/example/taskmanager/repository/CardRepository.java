package com.example.taskmanager.repository;

import com.example.taskmanager.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card,Long> {
    List<Card> findByUserId(Long userId);
}
