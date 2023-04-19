package com.example.demo.domain;

import lombok.Data;

@Data
public class User {
    private Long _id;
    private String userName;
    private String email;
    private String password;
}
