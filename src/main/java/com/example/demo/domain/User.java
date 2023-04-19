package com.example.demo.domain;

import lombok.Data;
import lombok.ToString;

/*
DTO
 */
@Data
@ToString
public class User {
    private Long userId;
    private String userName;
    private String email;
    private String password;
}
