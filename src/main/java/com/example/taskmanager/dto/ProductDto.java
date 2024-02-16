package com.example.taskmanager.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {
    private Long id;
    private String title;
    private String description;
    private String image;
    private String category;
    private BigDecimal price;
    private RateDto rating;
}
