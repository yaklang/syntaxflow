import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Example {
    public static void main(String[] args) {
        try {
            String data = "Hello, world!";
            Files.write(Paths.get("example.txt"), data.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}