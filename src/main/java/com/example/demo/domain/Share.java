package com.example.demo.domain;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/*
DTO
 */
@Data
@ToString
@Accessors(chain = true)
public class Share {
    private Long shareId;
    private Long userId;
    private String userName;
    private Long suitId;
    private Date shareTime;
    private Long heat;
}
