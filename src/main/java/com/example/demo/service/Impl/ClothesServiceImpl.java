package com.example.demo.service.Impl;

import com.example.demo.common.Common;
import com.example.demo.domain.Clothes;
import com.example.demo.domain.User;
import com.example.demo.mapper.AccountMapper;
import com.example.demo.mapper.ClothesMapper;
import com.example.demo.service.AccountService;
import com.example.demo.service.ClothesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClothesServiceImpl implements ClothesService {

    @Resource
    private ClothesMapper clothesMapper;


    @Override
    public List<List<Clothes>> getClothes(Integer id) {
        List<List<Clothes>> typeClothes = new ArrayList<>();
        for (int i = 0; i < Common.CLOTHES_TYPE; i++) typeClothes.add(new ArrayList<>());
        for (Clothes clothes : clothesMapper.findByUserId(id))
            typeClothes.get(clothes.getType() - 1).add(clothes);
        return typeClothes;
    }
}

