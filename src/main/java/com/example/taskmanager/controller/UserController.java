package com.example.taskmanager.controller;

import com.example.taskmanager.dto.Response;
import com.example.taskmanager.dto.request.LoginRequest;
import com.example.taskmanager.dto.request.UserDTORequest;
import com.example.taskmanager.dto.response.LoginResponse;
import com.example.taskmanager.dto.response.UserDTOResponse;
import com.example.taskmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping(value = "/admin")
    public Response<UserDTOResponse> signup(@RequestBody UserDTOResponse userDTO) {
        return new Response<>(HttpStatus.CREATED.value(), userService.addUser(userDTO));
    }
    //create admin account
    @PostMapping(value = "/login")
    public Response<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return new Response<>(HttpStatus.OK.value(), userService.login(loginRequest));
    }

    @GetMapping( value = "/{userId}")
    public Response<UserDTOResponse> getInfo(@PathVariable Long userId){
        return new Response<>(HttpStatus.OK.value(), userService.getInfo(userId));
    }
    @PutMapping( value = "/update/{userId}")
    public Response<UserDTOResponse> updateInfo(@PathVariable Long userId, @RequestBody UserDTORequest userDTOResponse) {
        return new Response<>(HttpStatus.OK.value(),userService.update(userDTOResponse,userId));
    }
    @DeleteMapping(value = "/delete/{userId}")
    public Response<Integer> delete(@PathVariable Long userId) {
        return new Response<>(HttpStatus.OK.value(),userService.delete(userId));
    }
}
