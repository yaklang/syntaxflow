import java.sql.*;

public class JdbcExample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/exampledb";
        String username = "root";
        String password = "password";

        try {
            // 加载和注册 JDBC 驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 建立连接
            Connection conn = DriverManager.getConnection(url, username, password);

            // 创建 Statement
            Statement stmt = conn.createStatement();

            // 执行查询
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");

            // 处理 ResultSet
            while (rs.next()) {
                System.out.println(rs.getString("username"));
            }

            // 关闭连接
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}