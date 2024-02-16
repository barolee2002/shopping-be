package com.example.taskmanager.service;

import com.example.taskmanager.dto.request.CardRequest;
import com.example.taskmanager.dto.response.CardResponse;

import java.util.List;

public interface CardService {
    List<CardResponse> getAll(Long userId);

    CardResponse save(CardRequest request, Long userId);

    Long delete(Long id);
}
