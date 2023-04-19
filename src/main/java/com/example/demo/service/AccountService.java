package com.example.demo.service;

public interface AccountService {
    String login(String email, String password);
    String register(String email, String password, String userName);
}
