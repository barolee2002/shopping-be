package com.example.taskmanager.dto.request;

import lombok.Data;

@Data
public class CardRequest {
    private Long productId;
    private Integer quantity;
}
