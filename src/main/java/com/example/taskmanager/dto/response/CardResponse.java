package com.example.taskmanager.dto.response;

import com.example.taskmanager.dto.ProductDto;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CardResponse {
    private Long id;
    private ProductDto product;
    private Integer quantity;
    private Date createAt;
}
