package com.example.demo.controller.contextreq;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
public class RestContextRequestController {
    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/xss/contextreq")
    public String unstandardRequestHandler() {
        request.getParameter("name");
        return "Name: " + request.getParameter("name") + "RequestURI: " + request.getRequestURI();
    }

    @RequestMapping("/xss/holderreq")
    public String unstandardRequestHandlerHolder() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (sra == null) {
            return "No request attributes";
        }
        HttpServletRequest request = sra.getRequest();
        request.getParameter("name");
        return "Name: " + request.getParameter("name") + "RequestURI: " + request.getRequestURI();
    }
}
