package lms;
import java.sql.*;

class ViewBook {
    public static void viewBooks() {
        String url = "jdbc:mysql://localhost:3307/library";
        String user = "root";
        String password = "rootpassword";

        String query = "SELECT * FROM books";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("\nBooks in Library:");
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
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}

