package lms;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class ReserveBook {
    // Define database connection details as constants
    private static final String URL = "jdbc:mysql://localhost:3307/library"; // Replace 'library' with your database name
    private static final String USER = "root"; // Replace with your database username
    private static final String PASSWORD = "rootpassword"; // Replace with your database password

    public static void reserveBook(int userId, int bookId) {
        String query = "SELECT status FROM books WHERE id = ?";
        String insertReservationQuery = "INSERT INTO reservations (user_id, book_id, reservation_date) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query);
             PreparedStatement insertStmt = conn.prepareStatement(insertReservationQuery)) {

            // Check the status of the book
            stmt.setInt(1, bookId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String status = rs.getString("status");

                if ("Available".equals(status)) {
                    System.out.println("This book is available. You can borrow it.");
                } else {
                    // Book is unavailable, so reserve it
                    insertStmt.setInt(1, userId);
                    insertStmt.setInt(2, bookId);
                    insertStmt.setDate(3, Date.valueOf(LocalDate.now()));

                    int rows = insertStmt.executeUpdate();
                    if (rows > 0) {
                        System.out.println("Book reserved successfully.");
                    } else {
                        System.out.println("Failed to reserve the book.");
                    }
                }
            } else {
                System.out.println("Book not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
