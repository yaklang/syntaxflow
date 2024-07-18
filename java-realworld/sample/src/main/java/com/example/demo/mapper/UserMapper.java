package com.example.demo.mapper;

import com.example.demo.model.UserModel;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserMapper {
    void insertUser(UserModel user);
    List<UserModel> findAllUsers();
}