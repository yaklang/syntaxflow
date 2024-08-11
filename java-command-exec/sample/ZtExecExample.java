package com.example.ztexec;

import com.github.zt.exec.Command;
import com.github.zt.exec.ExecutionReport;
import com.github.zt.exec.ProcessExecutor;

public class ZtExecExample {

    public static void main(String[] args) {
        // 定义要执行的命令
        Command command = new Command("ping", "-c", "4", "www.google.com"); // Linux系统
        // Command command = new Command("cmd", "/c", "ping", "www.google.com"); // Windows系统

        try {
            // 创建ProcessExecutor并执行命令
            ProcessExecutor executor = new ProcessExecutor(command);
            ExecutionReport report = executor.execute();

            // 获取并打印命令输出
            String output = report.getOutput();
            System.out.println("命令输出:\n" + output);

            // 获取并打印退出码
            int exitCode = report.getExitValue();
            System.out.println("退出码: " + exitCode);
        } catch (Exception e) {
            System.err.println("执行命令时发生异常: " + e.getMessage());
        }
    }
}