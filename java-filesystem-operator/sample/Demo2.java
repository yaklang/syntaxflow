import java.io.FileOutputStream;
import java.io.IOException;

public class Example {
    public static void main(String[] args) {
        try {
            FileOutputStream outputStream = new FileOutputStream("example.txt");
            String data = "Hello, world!";
            outputStream.write(data.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}