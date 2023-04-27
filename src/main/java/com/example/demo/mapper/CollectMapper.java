package com.example.demo.mapper;

import com.example.demo.domain.Share;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectMapper {

    List<Share> findByUserId(@Param("id") Long userId);

    void collect(@Param("shareId") Long shareId, @Param("userId") Long userId);

    void delete(@Param("shareId") Long shareId, @Param("userId") Long userId);

}
