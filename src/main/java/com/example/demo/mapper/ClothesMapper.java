package com.example.demo.mapper;

import com.example.demo.domain.Clothes;
import com.example.demo.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClothesMapper {

    List<Clothes> findByUserId(@Param("id") Integer userId);

}
