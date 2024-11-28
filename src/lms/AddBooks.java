package lms;

import java.sql.*;
import java.util.Scanner;

public class AddBooks {

    // Database credentials
    private static final String URL = "jdbc:mysql://localhost:3307/library";
    private static final String USER = "root";
    private static final String PASSWORD = "rootpassword";

    // Add a new book to the database
    public static void addBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter book ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter book category: ");
        String category = scanner.nextLine();
        System.out.print("Enter total copies: ");
        int totalCopies = scanner.nextInt();

        String query = "INSERT INTO books (title, author, isbn, category, total_copies, available_copies) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setString(3, isbn);
            stmt.setString(4, category);
            stmt.setInt(5, totalCopies);
            stmt.setInt(6, totalCopies);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book added successfully!");
            } else {
                System.out.println("Failed to add book.");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // Other methods like viewBooks(), addUser(), borrowBook(), etc., can go here
}
