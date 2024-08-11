package com.example.processbuilder;

import java.io.IOException;

public class ProcessBuilderExample {

    public static void main(String[] args) {
        // 创建ProcessBuilder实例，指定要启动的程序
        ProcessBuilder pb = new ProcessBuilder("notepad.exe");

        try {
            // 启动记事本
            Process process = pb.start();
            System.out.println("记事本已启动。");

            // 等待记事本关闭
            int exitCode = process.waitFor();
            System.out.println("记事本已关闭，退出码: " + exitCode);
        } catch (IOException e) {
            System.err.println("启动记事本时发生错误: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("等待记事本关闭时发生错误: " + e.getMessage());
        }
    }
}