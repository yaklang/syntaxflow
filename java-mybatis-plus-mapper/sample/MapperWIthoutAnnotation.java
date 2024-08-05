package com.mycompany.myapp;

public interface UserMapper {
    User getUser(int id);
    int insertUser(User user);
    void updateUser(User user);
    void deleteUser(int id);
}
