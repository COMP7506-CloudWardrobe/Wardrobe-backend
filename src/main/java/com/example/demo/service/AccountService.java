package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.domain.vo.UserLoginVO;

public interface AccountService {
    User login(String email, String password);
    User register(UserLoginVO userLoginVO);
}
