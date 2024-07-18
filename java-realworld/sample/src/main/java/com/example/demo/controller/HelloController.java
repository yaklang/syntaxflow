package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/rest/hello")
    public String hello() {
        return "hello this is demo in SyntaxFlow Realworld in @RestController";
    }
}