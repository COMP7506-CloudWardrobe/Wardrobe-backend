package com.example.demo.domain.vo;

import com.example.demo.domain.Clothes;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ClothesWardrobeVO {
    List<Clothes> tops;
    List<Clothes> bottoms;
    List<Clothes> one_pieces;
    List<Clothes> shoes;
    List<Clothes> accessories;
}
