package com.example;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/myServlet", asyncSupported = true)
public class MyServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        AsyncContext ac = req.startAsync();
        // 在这里你可以在另一个线程中处理 name 参数
        ac.start(new MyAsyncTask(name, ac));
    }
}

class MyAsyncTask implements Runnable {
    private String name;
    private AsyncContext ac;

    public MyAsyncTask(String name, AsyncContext ac) {
        this.name = name;
        this.ac = ac;
    }

    @Override
    public void run() {
        // 在这里你可以处理 name 参数
        // 并且使用 ac.complete() 来结束异步处理
    }
}