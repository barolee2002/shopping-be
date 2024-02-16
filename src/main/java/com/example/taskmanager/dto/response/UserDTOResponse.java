package com.example.taskmanager.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class UserDTOResponse {
    private Long id;
    private String name;
    private String username;
    private String password;
    private Date createAt;
    private Date updateAt;
}
