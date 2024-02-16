package com.example.taskmanager.service.impl;

import com.example.taskmanager.dto.request.CardRequest;
import com.example.taskmanager.dto.response.CardResponse;
import com.example.taskmanager.entity.Card;
import com.example.taskmanager.repository.CardRepository;
import com.example.taskmanager.service.CardService;
import com.example.taskmanager.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.LocalDate.now;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;
    private final ProductService productService;
    @Override
    public List<CardResponse> getAll(Long userId) {
        List< Card> cards = cardRepository.findByUserId(userId);
        List<CardResponse> response = cards.stream().map(card -> {
            CardResponse cardResponse = new CardResponse();
            cardResponse.setId(card.getId());
            cardResponse.setProduct(productService.getById(card.getProductId()));
            cardResponse.setQuantity(card.getQuantity());
            cardResponse.setCreateAt(card.getCreateAt());
            return cardResponse;
        }).collect(Collectors.toList());
        return response;
    }
    @Override
    public CardResponse save(CardRequest request, Long userId) {
        Date current = new Date();
        Card card = new Card();
        card.setUserId(userId);
        card.setProductId(request.getProductId());
        card.setQuantity(request.getQuantity());
        card.setCreateAt(current);
        card = cardRepository.save(card);
        CardResponse response = new CardResponse();
        response.setProduct(productService.getById(card.getProductId()));
        response.setQuantity(card.getQuantity());
        return response;

    }
    @Override
    public Long delete(Long id) {
        Card card = cardRepository.findById(id).get();
        cardRepository.delete(card);
        return id;
    }
}
