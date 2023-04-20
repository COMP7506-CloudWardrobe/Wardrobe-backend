package com.example.demo.domain;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@Accessors(chain = true)
public class Clothes {
    Long userId;
    Long clothesId;
    Integer type;
}
