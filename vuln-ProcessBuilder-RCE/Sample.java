import java.io.*;

public class TestRCE {
    public static void main(String[] args) {
        String cmd = "ping example.com";
        
        // 这行代码将被上面的规则捕获
        ProcessBuilder pb = new ProcessBuilder(cmd.split(" "));
        pb.start();
    }
}