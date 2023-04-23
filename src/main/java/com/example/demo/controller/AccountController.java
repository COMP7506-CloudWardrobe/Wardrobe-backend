package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.domain.vo.UserLoginVO;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<UserLoginVO> login(@RequestParam String email, @RequestParam String password) {
        User user = accountService.login(email, password);
        if (user == null) {
            return ResponseEntity.ok(null);
        }
        UserLoginVO userLoginVO = new UserLoginVO();
        userLoginVO.setEmail(email);
        userLoginVO.setPassword(password);
        userLoginVO.setUserId(user.getUserId());
        userLoginVO.setUserName(user.getUserName());
        return ResponseEntity.ok(userLoginVO);
    }

    @PostMapping("/register")
    public ResponseEntity<UserLoginVO> login(@RequestBody UserLoginVO userLoginVO) {
        User user = accountService.register(userLoginVO);
        if (user == null) {
            return ResponseEntity.ok(null);
        }
        userLoginVO.setUserId(user.getUserId());
        return ResponseEntity.ok(userLoginVO);
    }
}
