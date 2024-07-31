package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter("/myServlet")
public class MyFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        String name = req.getParameter("name");
        // 在这里你可以对 name 参数进行预处理
        chain.doFilter(req, res);
        // 在这里你可以对响应进行后处理
    }
}

