package com.example.mapper;

import com.example.User;
import org.apache.ibatis.annotations.Param;
import java.util.Map;

public interface UserMapper {
    void insertUser(@Param("tableName") String tableName, @Param("user") User user);

    User selectUserByUsername(@Param("tableName") String tableName,
                              @Param("columnName") String columnName,
                              @Param("value") String value);

    void updateUserEmail(@Param("tableName") String tableName,
                         @Param("email") String email,
                         @Param("username") String username);

    void deleteUser(@Param("tableName") String tableName,
                    @Param("username") String username);
}