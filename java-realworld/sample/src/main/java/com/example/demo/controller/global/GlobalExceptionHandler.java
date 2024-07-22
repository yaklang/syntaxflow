package com.example.demo.controller.global;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleException(Exception ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        // 打印异常信息到控制台
        ex.printStackTrace(pw);
        // 将异常信息返回到结果中
        return new ResponseEntity<>(sw.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
