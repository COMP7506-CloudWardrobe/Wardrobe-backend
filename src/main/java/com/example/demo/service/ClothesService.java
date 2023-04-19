package com.example.demo.service;

import com.example.demo.domain.Clothes;

import java.util.List;

public interface ClothesService {
    List<List<Clothes>> getClothes(Integer id);
}
