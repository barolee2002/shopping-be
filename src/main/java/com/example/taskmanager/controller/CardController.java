package com.example.taskmanager.controller;

import com.example.taskmanager.dto.Response;
import com.example.taskmanager.dto.request.CardRequest;
import com.example.taskmanager.dto.response.CardResponse;
import com.example.taskmanager.entity.Card;
import com.example.taskmanager.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;
    @GetMapping("list-all/{userId}")
    public Response<List<CardResponse>> getAll(@PathVariable Long userId) {
        return  new Response<>(HttpStatus.OK.value(), cardService.getAll(userId));
    }
    @PostMapping("save/{userId}")
    public Response<CardResponse> save(@PathVariable Long userId, @RequestBody CardRequest request) {
        return new Response<>(HttpStatus.OK.value(), cardService.save(request,userId));
    }
    @DeleteMapping("/delete/{cardId}")
    public Response<Long> delete(@PathVariable Long cardId) {
        return new Response<>(HttpStatus.OK.value(), cardService.delete(cardId));
    }
}
