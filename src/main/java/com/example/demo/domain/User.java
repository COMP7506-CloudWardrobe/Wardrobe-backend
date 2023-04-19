package com.example.demo.domain;

import lombok.Data;

/*
DTO
 */
@Data
public class User {
    private Long id;
    private String userName;
    private String email;
    private String password;
}
