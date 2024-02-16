package com.example.taskmanager.service;

import com.example.taskmanager.dto.request.LoginRequest;
import com.example.taskmanager.dto.request.UserDTORequest;
import com.example.taskmanager.dto.response.LoginResponse;
import com.example.taskmanager.dto.response.UserDTOResponse;

public interface UserService {
    UserDTOResponse addUser(UserDTOResponse userDTO);


    LoginResponse login(LoginRequest loginRequest);


    UserDTOResponse update(UserDTORequest userDTO, Long userId);

    UserDTOResponse getInfo(Long userId);


    Integer delete(Long userId);
}
