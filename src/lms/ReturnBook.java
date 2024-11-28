package lms;
import java.sql.*;
import java.util.*;

public class ReturnBook {
    public static void returnBook(int borrowId) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter borrow record ID: ");
        int borrowIds = scanner.nextInt();

        String url = "jdbc:mysql://localhost:3307/library";
        String user = "root";
        String password = "rootpassword";

        String returnQuery = "UPDATE borrow_records SET return_date = CURDATE() WHERE id = ?";
        String updateBookQuery = "UPDATE books SET available_copies = available_copies + 1 WHERE id = (SELECT book_id FROM borrow_records WHERE id = ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement returnStmt = conn.prepareStatement(returnQuery);
             PreparedStatement updateStmt = conn.prepareStatement(updateBookQuery)) {

            conn.setAutoCommit(false);

            // Update return date in borrow records
            returnStmt.setInt(1,  borrowIds);
            returnStmt.executeUpdate();

            // Update book availability
            updateStmt.setInt(1,  borrowIds);
            int rowsUpdated = updateStmt.executeUpdate();

            if (rowsUpdated > 0) {
                conn.commit();
                System.out.println("Book returned successfully!");
            } else {
                conn.rollback();
                System.out.println("Failed to return book.");
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

}
