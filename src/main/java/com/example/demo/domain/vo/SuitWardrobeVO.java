package com.example.demo.domain.vo;

import com.example.demo.domain.Suit;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class SuitWardrobeVO {
    List<Suit> suitList;
}
