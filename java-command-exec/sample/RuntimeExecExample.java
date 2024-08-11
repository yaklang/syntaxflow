package com.example.runtimeexec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RuntimeExecExample {

    public static void main(String[] args) {
        // 定义要执行的命令
        String command = "cmd /c dir"; // Windows系统
        // String command = "ls"; // Linux系统

        try {
            // 执行命令
            Process process = Runtime.getRuntime().exec(command);

            // 获取命令的输出流
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            StringBuilder output = new StringBuilder();

            // 读取输出
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // 等待命令执行完毕
            int exitCode = process.waitFor();

            // 打印输出和退出码
            System.out.println("Command Output:\n" + output.toString());
            System.out.println("Exit Code: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}