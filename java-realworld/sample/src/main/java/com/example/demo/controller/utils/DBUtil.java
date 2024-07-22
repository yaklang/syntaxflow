package com.example.demo.controller.utils;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class DBUtil {

    private static JdbcTemplate jdbcTemplate = null;

    public static void setJdbcTemplate(JdbcTemplate tmp) {
        jdbcTemplate = tmp;
    }

    public static List<Map<String, Object>> querySomeResult(String whereCondition) {
        String sql = "SELECT * FROM your_table WHERE " + whereCondition;
        return jdbcTemplate.queryForList(sql);
    }
}