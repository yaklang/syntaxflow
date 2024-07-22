package com.example.demo.controller.utils;

public class DummyUtil {
    public static String filterXSS(String s) {
        return s.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
    }

    public static String nothing(String s) {
        return s;
    }
}
