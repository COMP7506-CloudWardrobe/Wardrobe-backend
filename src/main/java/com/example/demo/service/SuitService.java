package com.example.demo.service;

import com.example.demo.domain.Clothes;
import com.example.demo.domain.Suit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SuitService {
    List<Suit> getSuits(Integer id);

    Long uploadSuit(Suit suit);

    Long deleteSuit(Long suitId, Long userId);
}
