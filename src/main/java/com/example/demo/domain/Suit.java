package com.example.demo.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Suit {
    Long userId;
    Long suitId;
    Long topId;
    Long bottomId;
    Long outwearId;
    Long shoesId;
    Long accessoryId1;
    Long accessoryId2;
}
