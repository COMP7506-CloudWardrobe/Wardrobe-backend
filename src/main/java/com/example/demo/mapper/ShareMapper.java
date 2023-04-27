package com.example.demo.mapper;

import com.example.demo.domain.Share;
import com.example.demo.domain.Suit;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShareMapper {

    List<Share> getAll();

    List<Share> findByUserId(@Param("id") Long userId);

    Share findByShareId(@Param("shareId") Long shareId);

    void shareSuit(@Param("share") Share share);

    void deleteShare(@Param("shareId") Long id);

}
