package com.example.nuprocess;

import com.zaxxer.nuprocess.NuAbstractProcessHandler;
import com.zaxxer.nuprocess.NuProcess;
import com.zaxxer.nuprocess.NuProcessBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class NuProcessExample {

    public static void main(String[] args) {
        // 定义要执行的命令
        NuProcessBuilder pb = new NuProcessBuilder(Arrays.asList("ping", "-c", "4", "www.google.com")); // Linux系统
        // NuProcessBuilder pb = new NuProcessBuilder(Arrays.asList("cmd", "/c", "ping", "www.google.com")); // Windows系统

        // 设置进程监听器
        pb.setProcessListener(new ProcessHandler());
        
        // 启动进程
        NuProcess process = pb.start();
        
        // 表示我们有数据要写入
        process.wantWrite();
        
        // 等待进程完成
        process.waitFor(0, TimeUnit.SECONDS); // 0表示无限等待
    }

    private static class ProcessHandler extends NuAbstractProcessHandler {
        @Override
        public void onStart(NuProcess nuProcess) {
            System.out.println("进程已启动: " + nuProcess);
        }

        @Override
        public void onStdout(byte[] buffer, int offset, int length) {
            System.out.write(buffer, offset, length);
        }

        @Override
        public void onStderr(byte[] buffer, int offset, int length) {
            System.err.write(buffer, offset, length);
        }

        @Override
        public void onExit(int exitCode) {
            System.out.println("进程已退出，退出码: " + exitCode);
        }
    }
}