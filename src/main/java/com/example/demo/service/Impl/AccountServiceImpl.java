package com.example.demo.service.Impl;

import com.example.demo.domain.User;
import com.example.demo.mapper.AccountMapper;
import com.example.demo.service.AccountService;
//import jakarta.annotation.Resource;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Override
    public String login(String email, String password) {
        System.out.println(email);
        User user = accountMapper.findByEmail(email);
        System.out.println(user);
        if (user == null || !user.getPassword().equals(password)) {
            return null;
        }
        String accountToken = generateToken();
//        user.setUserToken(accountToken);
//        accountMapper.updateAccount(user);
        return accountToken;
    }

    @Override
    public String register(String email, String password, String userName) {
        User user = accountMapper.findByEmail(email);
        if (user != null) {
            return null;
        }
        user = new User();
        user.setUserName(userName);
        user.setEmail(email);
        user.setPassword(password);
        String accountToken = generateToken();
        accountMapper.insertAccount(user);
        return accountToken;
    }

    private String generateToken() {
        // 生成token
        return "tmp token";
    }
}

