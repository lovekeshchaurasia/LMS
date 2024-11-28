package lms;
import java.sql.*;
import java.util.*;

public class AddUser {
    public static void addUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter user name: ");
        String name = scanner.nextLine();
        System.out.print("Enter user role (Admin/User): ");
        String role = scanner.nextLine();
        System.out.print("Enter user email: ");
        String email = scanner.nextLine();
        System.out.print("Enter user phone: ");
        String phone = scanner.nextLine();

        String url = "jdbc:mysql://localhost:3307/library";
        String user = "root";
        String password = "rootpassword";

        String query = "INSERT INTO users (name, role, email, phone) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, role);
            stmt.setString(3, email);
            stmt.setString(4, phone);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User added successfully!");
            } else {
                System.out.println("Failed to add user.");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

}
