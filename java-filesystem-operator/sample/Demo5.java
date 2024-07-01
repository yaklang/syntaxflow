import java.io.PrintWriter;
import java.io.IOException;

public class Example {
    public static void main(String[] args) {
        try {
            PrintWriter writer = new PrintWriter("example.txt", "UTF-8");
            writer.println("Hello, world!");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}