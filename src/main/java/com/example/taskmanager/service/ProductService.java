package com.example.taskmanager.service;

import com.example.taskmanager.dto.BasePage;
import com.example.taskmanager.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto save(ProductDto dto);

    ProductDto getById(Long productId);

    BasePage<ProductDto> getAll(String searchString, int page, int pageSize);

    List<ProductDto> getBycategory(String category);
}
