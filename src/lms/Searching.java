package lms;
import java.util.*;
import java.sql.*;

public class Searching {
    public static void searchBooks() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter keyword to search (title, author, or category): ");
        String keyword = scanner.nextLine();

        String url = "jdbc:mysql://localhost:3307/library";
        String user = "root";
        String password = "rootpassword";

        String query = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ? OR category LIKE ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            stmt.setString(3, "%" + keyword + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("\nSearch Results:");
                System.out.printf("%-5s %-30s %-20s %-15s %-10s %-10s%n", "ID", "Title", "Author", "ISBN", "Category", "Available");
                while (rs.next()) {
                    System.out.printf("%-5d %-30s %-20s %-15s %-10s %-10d%n",
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getString("isbn"),
                            rs.getString("category"),
                            rs.getInt("available_copies"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

}
