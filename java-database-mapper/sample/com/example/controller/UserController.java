package com.example.controller;

import com.example.User;
import com.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/create")
    public String createUser(@RequestParam String tableName, @RequestBody User user) {
        userMapper.insertUser(tableName, user);
        return "User created successfully!";
    }

    @GetMapping("/get")
    public User getUser(@RequestParam String tableName, @RequestParam String columnName, @RequestParam String username) {
        return userMapper.selectUserByUsername(tableName, columnName, username);
    }

    @PutMapping("/update")
    public String updateUserEmail(@RequestParam String tableName, @RequestParam String username, @RequestParam String email) {
        userMapper.updateUserEmail(tableName, email, username);
        return "User email updated successfully!";
    }

    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam String tableName, @RequestParam String username) {
        userMapper.deleteUser(tableName, username);
        return "User deleted successfully!";
    }
}