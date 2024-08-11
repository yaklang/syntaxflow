package com.example.hadoop;

import org.apache.hadoop.util.Shell;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ShellCommandExecutorExample {

    public static void main(String[] args) {
        // 定义要执行的命令
        String[] command = {"ping", "-c", "4", "www.google.com"}; // Linux系统
        // String[] command = {"cmd", "/c", "ping", "www.google.com"}; // Windows系统

        // 创建环境变量（可选）
        Map<String, String> env = new HashMap<>();
        env.put("MY_ENV_VAR", "some_value");

        // 创建ShellCommandExecutor实例
        Shell.ShellCommandExecutor executor = new Shell.ShellCommandExecutor(command, null, env);

        try {
            // 执行命令
            executor.execute();
            // 获取命令输出
            String output = executor.getOutput();
            System.out.println("命令输出:\n" + output);
        } catch (IOException e) {
            System.err.println("执行命令时发生IO异常: " + e.getMessage());
        } catch (Shell.ExitCodeException e) {
            System.err.println("命令执行失败，退出码: " + e.getExitCode());
        }
    }
}