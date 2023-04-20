package com.example.demo.service.Impl;

import com.example.demo.common.Common;
import com.example.demo.config.StorageConfig;
import com.example.demo.domain.User;
import com.example.demo.domain.vo.UserLoginVO;
import com.example.demo.mapper.AccountMapper;
import com.example.demo.service.AccountService;
//import jakarta.annotation.Resource;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Autowired
    private StorageConfig storageConfig;

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

        System.out.println(user);

        File clothesDirectory = new File(Paths.get(storageConfig.getBasePath())
                .resolve(Common.USER + user.getUserId())
                .resolve(Common.CLOTHES_DIR).toUri());
        if (!clothesDirectory.exists()) clothesDirectory.mkdirs();

        File suitDirectory = new File(Paths.get(storageConfig.getBasePath())
                .resolve(Common.USER + user.getUserId())
                .resolve(Common.SUIT_DIR).toUri());
        if (!suitDirectory.exists()) suitDirectory.mkdirs();

        return user;
    }
}

