package com.example.taskmanager.repository;
import com.example.taskmanager.entity.Product;
import com.example.taskmanager.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateRepository extends JpaRepository<Rate,Long> {
    Rate findByProductId(Long productId);
}
