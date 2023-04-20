package com.example.demo.service.Impl;

import com.example.demo.domain.User;
import com.example.demo.domain.vo.UserLoginVO;
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
    public User login(String email, String password) {
        return accountMapper.findByEmail(email);
    }

    @Override
    public User register(UserLoginVO vo) {
        User user = accountMapper.findByEmail(vo.getEmail());
        if (user != null) {
            return null;
        }
        user = new User();
        user.setUserName(vo.getUserName());
        user.setEmail(vo.getEmail());
        user.setPassword(vo.getPassword());
        accountMapper.insertAccount(user);
        return user;
    }
}

