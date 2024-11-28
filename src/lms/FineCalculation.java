package lms;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FineCalculation {

    private static final String URL = "jdbc:mysql://localhost:3307/library";
    private static final String USER = "root";
    private static final String PASSWORD = "your_password";

    // Method to return a book and calculate the fine
    public static void returnBook(int borrowId) {
        String query = "SELECT borrow_date, due_date FROM borrow_records WHERE borrow_id = ?";
        String updateQuery = "UPDATE borrow_records SET return_date = ?, fine_amount = ? WHERE borrow_id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query);
             PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {

            // Fetch borrow and due dates
            stmt.setInt(1, borrowId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                LocalDate borrowDate = rs.getDate("borrow_date").toLocalDate();
                LocalDate dueDate = rs.getDate("due_date").toLocalDate();
                LocalDate returnDate = LocalDate.now();
                long overdueDays = ChronoUnit.DAYS.between(dueDate, returnDate);
                double fine = overdueDays > 0 ? overdueDays * 10 : 0; // 10 units fine per day

                // Update the record with return date and fine
                updateStmt.setDate(1, Date.valueOf(returnDate));
                updateStmt.setDouble(2, fine);
                updateStmt.setInt(3, borrowId);

                int rows = updateStmt.executeUpdate();
                if (rows > 0) {
                    System.out.println("Book returned successfully!");
                    if (fine > 0) {
                        System.out.println("Fine imposed: " + fine);
                    }
                } else {
                    System.out.println("Failed to return book.");
                }
            } else {
                System.out.println("No borrow record found with the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

