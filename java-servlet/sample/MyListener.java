package com.example;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        String name = sce.getServletContext().getInitParameter("name");
        // 在这里你可以对 name 参数进行初始化操作
    }
}

