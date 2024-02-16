package com.example.taskmanager.dto.response;

import com.example.taskmanager.dto.ProductDto;
import lombok.Data;

@Data
public class CardProduct {
    private ProductDto product;
    private Integer quantity;
}
