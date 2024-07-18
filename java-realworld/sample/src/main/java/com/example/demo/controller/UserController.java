package com.example.demo.controller;

import com.example.demo.model.UserModel;
import com.example.demo.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserMapper userMapper;

    public UserController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @PostMapping
    public void addUser(@RequestBody UserModel user) {
        userMapper.insertUser(user);
    }

    @GetMapping
    public List<UserModel> getAllUsers() {
        return userMapper.findAllUsers();
    }
}