package com.example.demo.service;

import com.example.demo.domain.Share;

import java.util.List;

public interface CollectService {
    List<Share> findByUserId(Long userId);

    void collect(Long userId, Long shareId);

    void delete(Long userId, Long shareId);
}
