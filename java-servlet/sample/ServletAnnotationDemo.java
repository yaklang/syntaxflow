package com.example;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebServlet(urlPatterns = "/myServlet", initParams = {
    @WebInitParam(name = "name", value = "MyName")
})
public class MyServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String name = this.getServletConfig().getInitParameter("name");
        // 在这里你可以处理 name 参数
    }
}

