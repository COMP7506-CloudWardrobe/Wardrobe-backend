package com.example.demo.controller;

import com.example.demo.domain.vo.UserLoginVO;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<UserLoginVO> login(@RequestParam String email, @RequestParam String password) {
        String accountToken = accountService.login(email, password);
        if (accountToken == null) {
            return ResponseEntity.notFound().build();
        }
        UserLoginVO userLoginVO = new UserLoginVO();
        userLoginVO.setEmail(email);
        userLoginVO.setPassword(password);
        userLoginVO.setUserToken(accountToken);
        return ResponseEntity.ok(userLoginVO);
    }
}
