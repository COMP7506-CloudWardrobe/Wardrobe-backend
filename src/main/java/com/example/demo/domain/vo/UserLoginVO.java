package com.example.demo.domain.vo;

import lombok.Data;

@Data
public class UserLoginVO {
    private Long userId;
    private String userName;
    private String email;
    private String password;
    private String userToken; // 暂时不用
}
