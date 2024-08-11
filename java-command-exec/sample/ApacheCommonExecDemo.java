package com.example.apachecommons;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ApacheCommonExecDemo {

    public static void main(String[] args) {
        // 定义要执行的命令
        String command = "ping -c 4 www.google.com"; // 在Linux上使用
        // String command = "ping www.google.com"; // 在Windows上使用

        // 创建输出流以接收标准输出和错误输出
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ByteArrayOutputStream errorStream = new ByteArrayOutputStream();

        // 创建CommandLine对象
        CommandLine commandLine = CommandLine.parse(command);

        // 创建DefaultExecutor对象
        DefaultExecutor executor = new DefaultExecutor();

        // 设置输出流处理
        PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream, errorStream);
        executor.setStreamHandler(streamHandler);

        // 设置超时（可选）
        ExecuteWatchdog watchdog = new ExecuteWatchdog(30000); // 设置超时为30秒
        executor.setWatchdog(watchdog);

        try {
            // 执行命令
            int exitValue = executor.execute(commandLine);
            System.out.println("命令执行成功，退出码: " + exitValue);
            System.out.println("标准输出:\n" + outputStream.toString("UTF-8"));
        } catch (ExecuteException e) {
            System.err.println("命令执行失败，退出码: " + e.getExitValue());
            System.err.println("错误输出:\n" + errorStream.toString("UTF-8"));
        } catch (IOException e) {
            System.err.println("执行命令时发生IO异常: " + e.getMessage());
        }
    }
}