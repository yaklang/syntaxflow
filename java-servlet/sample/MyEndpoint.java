package com.example;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/myEndpoint")
public class MyEndpoint {
    @OnMessage
    public String handleMessage(String name, Session session) {
        // 在这里你可以处理 name 参数，并将结果发送回客户端
        return "Hello, " + name;
    }
}

