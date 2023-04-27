package com.example.demo.domain.vo;

import com.example.demo.domain.Share;
import com.example.demo.domain.Suit;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ShareListVO {
    List<Share> shareList;
}
