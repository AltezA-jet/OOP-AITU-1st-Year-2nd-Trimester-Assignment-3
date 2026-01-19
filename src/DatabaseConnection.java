import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/music_library";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Skb4k5o6orb";

    public static Connection getConnection() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println(" Connected to PostgreSQL: " + conn.getMetaData().getURL());
            return conn;
        } catch (SQLException e) {
            System.out.println(" Connection failed: " + e.getMessage());
            throw e;
        }
    }
}
