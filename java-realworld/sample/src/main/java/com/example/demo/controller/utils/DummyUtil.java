package com.example.demo.controller.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class DummyUtil {
    public static String filterXSS(String s) {
        return s.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
    }

    public static String nothing(String s) {
        return s;
    }

    public static String getParameter(String s) {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (sra == null) {
            return "";
        }
        HttpServletRequest req = sra.getRequest();
        return req.getParameter(s);
    }

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (sra == null) {
            return null;
        }
        return sra.getRequest();
    }
}
