package com.example.taskmanager.repository;

import com.example.taskmanager.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("SELECT e FROM Product e WHERE  :searchString IS NULL OR :searchString = '' "+
            "OR e.title LIKE %:searchString% OR e.category LIKE %:searchString%")
    Page<Product> getAllProduct(
            @Param("searchString") String searchString,
            Pageable pageable
    );
    List<Product> findByCategory(String category);
}
