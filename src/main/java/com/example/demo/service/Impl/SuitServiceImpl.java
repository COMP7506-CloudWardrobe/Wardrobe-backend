package com.example.demo.service.Impl;

import com.example.demo.common.Common;
import com.example.demo.domain.Clothes;
import com.example.demo.domain.Suit;
import com.example.demo.mapper.ClothesMapper;
import com.example.demo.mapper.SuitMapper;
import com.example.demo.service.ClothesService;
import com.example.demo.service.SuitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SuitServiceImpl implements SuitService {

    @Resource
    SuitMapper suitMapper;

    @Override
    public List<Suit> getSuits(Integer id) {
        return suitMapper.findByUserId(id);
    }
}

