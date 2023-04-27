package com.example.demo.service;

import com.example.demo.domain.Share;
import com.example.demo.domain.Suit;

import java.util.List;

public interface ShareService {
    List<Share> getAll();

    List<Share> findByUserId(Long userId);

    Share shareSuit(Long userId, Long suitId);

    void deleteShare(Long shareId);
}
