package lms;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class BorrowBook {
//    public static void borrowBook(int userId, int bookId) {
//        String URL = "jdbc:mysql://localhost:3307/library";
//        String USER = "root";
//        String PASSWORD = "rootpassword";
//
//        String checkUserQuery = "SELECT * FROM users WHERE id = ?";
//        String checkBookQuery = "SELECT * FROM books WHERE id = ?";
//        String borrowQuery = "INSERT INTO borrow_records ( borrow_id ,  user_id ,  book_id ) VALUES (?, ?, ?)";
//
//        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
//             PreparedStatement checkUserStmt = conn.prepareStatement(checkUserQuery);
//             PreparedStatement checkBookStmt = conn.prepareStatement(checkBookQuery);
//             PreparedStatement borrowStmt = conn.prepareStatement(borrowQuery)) {
//
//            // Check if the user exists
//            checkUserStmt.setInt(1, userId);
//            ResultSet userRs = checkUserStmt.executeQuery();
//            if (!userRs.next()) {
//                System.out.println("User not found. Please register the user first.");
//                return;
//            }
//
//            // Check if the book exists and is available
//            checkBookStmt.setInt(1, bookId);
//            ResultSet bookRs = checkBookStmt.executeQuery();
//            if (!bookRs.next()) {
//                System.out.println("Book not found.");
//                return;
//            }
//            String status = bookRs.getString("status");
//            if (!"Available".equalsIgnoreCase(status)) {
//                System.out.println("Book is not available for borrowing.");
//                return;
//            }
//
//            // Borrow the book
//            borrowStmt.setInt(1, userId);
//            borrowStmt.setInt(2, bookId);
//            borrowStmt.setDate(3, Date.valueOf(LocalDate.now()));
//
//            int rows = borrowStmt.executeUpdate();
//            if (rows > 0) {
//                System.out.println("Book borrowed successfully.");
//                // Update the book status to "Unavailable"
//                String updateBookStatusQuery = "UPDATE books SET status = 'Unavailable' WHERE book_id = ?";
//                try (PreparedStatement updateStmt = conn.prepareStatement(updateBookStatusQuery)) {
//                    updateStmt.setInt(1, bookId);
//                    updateStmt.executeUpdate();
//                }
//            } else {
//                System.out.println("Failed to borrow the book.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
public static void borrowBook(int userId, int bookId) {
    String URL = "jdbc:mysql://localhost:3307/library";
    String USER = "root";
    String PASSWORD = "rootpassword";

    String checkBookQuery = "SELECT id FROM books WHERE id = ?";
    String borrowQuery = "INSERT INTO borrow_records (user_id ,  book_id) VALUES (?, ?)";

    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement checkBookStmt = conn.prepareStatement(checkBookQuery);
         PreparedStatement borrowStmt = conn.prepareStatement(borrowQuery)) {

        // Check if the book exists
        checkBookStmt.setInt(1, bookId);
        ResultSet bookRs = checkBookStmt.executeQuery();
        if (!bookRs.next()) {
            System.out.println("Book not found with ID: " + bookId);
            return;
        }

        // Debugging: Print values before execution
        System.out.println("Borrowing Book ID: " + bookId + " by User ID: " + userId);

        // Borrow the book
        borrowStmt.setInt(1, userId);
        borrowStmt.setInt(2, bookId);
//        borrowStmt.setDate(3, Date.valueOf(LocalDate.now()));

        int rows = borrowStmt.executeUpdate();
        if (rows > 0) {
            System.out.println("Book borrowed successfully.");
        } else {
            System.out.println("Failed to borrow the book.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


}
