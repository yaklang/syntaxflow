package com.demo;

import com.github.mhashim6.systemcommandexecutor.Command;
import com.github.mhashim6.systemcommandexecutor.CommandBuilder;
import com.github.mhashim6.systemcommandexecutor.CommandExecutor;
import com.github.mhashim6.systemcommandexecutor.ExecutionReport;
import com.github.mhashim6.systemcommandexecutor.ProcessMonitor;
import com.github.mhashim6.systemcommandexecutor.exceptions.UnrecognisedCmdException;

import java.io.IOException;

public class SystemCommandExecutorExample {

    public static void main(String[] args) {
        // 构建ping命令
        Command cmd = new CommandBuilder()
                .forCommandLine("ping")
                .withArgs("www.google.com")
                .build();

        // 创建输出处理器
        ExecutionOutputPrinter outputPrinter = new ExecutionOutputPrinter();

        try {
            // 执行命令并重定向输出
            ProcessMonitor pMonitor = CommandExecutor.execute(cmd, null, outputPrinter);
            ExecutionReport report = pMonitor.getExecutionReport();

            // 获取退出码
            int exitCode = report.exitValue();
            System.out.printf("命令行: %s\n执行完成,退出码: %d\n", cmd.string(), exitCode);
        } catch (UnrecognisedCmdException e) {
            System.err.println("无法识别的命令: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("执行命令时发生IO异常: " + e.getMessage());
        }
    }

    private static class ExecutionOutputPrinter implements com.github.mhashim6.systemcommandexecutor.Appender {
        @Override
        public void appendStdText(String text) {
            System.out.println("标准输出: " + text);
        }

        @Override
        public void appendErrText(String text) {
            System.err.println("错误输出: " + text);
        }
    }
}