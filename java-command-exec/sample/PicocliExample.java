package com.example.picocli;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Command(name = "PingCommand", description = "Ping a host and display the result.")
public class PicocliExample implements Runnable {

    @Option(names = {"-h", "--host"}, description = "Host to ping", required = true)
    private String host;

    public static void main(String[] args) {
        CommandLine.run(new PicocliExample(), args);
    }

    @Override
    public void run() {
        String command = "ping " + host;

        try {
            // 执行命令
            Process process = Runtime.getRuntime().exec(command);

            // 读取输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            StringBuilder output = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // 等待命令执行完毕
            int exitCode = process.waitFor();

            // 打印输出和退出码
            System.out.println("命令输出:\n" + output.toString());
            System.out.println("退出码: " + exitCode);
        } catch (IOException e) {
            System.err.println("执行命令时发生IO异常: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("等待命令执行时被中断: " + e.getMessage());
        }
    }
}