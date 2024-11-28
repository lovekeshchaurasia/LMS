package lms;
import java.util.*;
import java.sql.*;

public class GenerateReports {
    public static void generateReports() {
        String url = "jdbc:mysql://localhost:3307/library";
        String user = "root";
        String password = "rootpassword";

        String query = "SELECT b.title, COUNT(r.book_id) AS borrow_count FROM books b JOIN borrow_records r ON b.id = r.book_id GROUP BY r.book_id ORDER BY borrow_count DESC";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("\nMost Borrowed Books:");
            System.out.printf("%-30s %-10s%n", "Title", "Borrow Count");
            // if did not bought even single book
            if(!(rs.next())) {
                System.out.println("You have not bought any book");
            }
            while (rs.next()) {
                System.out.printf("%-30s %-10d%n", rs.getString("title"), rs.getInt("borrow_count"));
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

}
