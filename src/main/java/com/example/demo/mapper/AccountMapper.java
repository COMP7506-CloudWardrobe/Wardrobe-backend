package com.example.demo.mapper;

import com.example.demo.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

//@Mapper
@Repository
//@Mapper
public interface AccountMapper {

    User findByEmail(@Param("email") String email);

    void insertAccount(User user);

    void updateAccount(User user);

}
