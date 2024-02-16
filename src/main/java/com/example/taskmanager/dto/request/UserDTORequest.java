package com.example.taskmanager.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class UserDTORequest {
    private String name;
    private String username;
    private String password;
}
