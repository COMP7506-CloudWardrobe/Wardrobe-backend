package com.example.demo.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class Suit {
    Long userId;
    Long suitId;
    Long topId;
    Long bottomId;
    Long onePieceId;
    Long shoesId;
    Long accessoryId;
}
