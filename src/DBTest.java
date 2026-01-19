import java.sql.Connection;

public class DBTest {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            System.out.println("🎯 Database connection is working!");
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}
