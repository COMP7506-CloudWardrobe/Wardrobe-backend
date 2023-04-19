package com.example.demo.mapper;

import com.example.demo.domain.Clothes;
import com.example.demo.domain.Suit;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuitMapper {

    List<Suit> findByUserId(@Param("id") Integer userId);

}
