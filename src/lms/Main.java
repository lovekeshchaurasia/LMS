//package lms;
//
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        while (true) {
//            System.out.println("\nLibrary Management System");
//            System.out.println("1. Add a Book");
//            System.out.println("2. View All Books");
//            System.out.println("3. Add a User");
//            System.out.println("4. Borrow a Book");
//            System.out.println("5. Return a Book");
//            System.out.println("6. Search Books");
//            System.out.println("7. Reserve a Book");
//            System.out.println("8. Generate Reports");
//            System.out.println("9. Exit");
//            System.out.print("Enter your choice: ");
//
//            int choice = sc.nextInt();
//            sc.nextLine(); // Consume newline left-over
//            switch (choice) {
//                case 1 -> AddBooks.addBook(); // Call the addBook method from AddBook class
//                case 2 -> ViewBook.viewBooks();
//                case 3 -> AddUser.addUser(); // Call the addUser method from AddUser class
//                case 4 -> {
//                    System.out.println("Enter yout userid");
//                    int userId = sc.nextInt();
//                    System.out.println("Enter your bookId");
//                    int bookId = sc.nextInt();
//                    BorrowBook.borrowBook(userId , bookId);}
//                case 5 -> {
//                    System.out.println("Enter brrow record id to return :");
//                    int borrowId = sc.nextInt();
//                    ReturnBook.returnBook(borrowId);
//                }
//                case 6 -> Searching.searchBooks();
//                case 7  -> {
//                    System.out.print("Enter user ID: ");
//                    int userId = sc.nextInt();
//                    System.out.print("Enter book ID to reserve: ");
//                    int bookId = sc.nextInt();
//                    ReserveBook.reserveBook(userId, bookId);
//                }
//                case 8 -> GenerateReports.generateReports();
//                case 9 -> {
//                    System.out.println("Exiting...!");
//                    sc.close();
//                    System.exit(0);
//                }
//                default -> System.out.println("Invalid choice. Please try again.");
//            }
//        }
//    }
//}
package lms;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to the Library Management System");
        System.out.println("Are you an Admin or a User?");
        System.out.print("Enter (Admin/User): ");
        String role = sc.nextLine();

        if (role.equalsIgnoreCase("Admin")) {
            AdminPanel adminPanel = new AdminPanel();
            adminPanel.displayOptions();
        } else if (role.equalsIgnoreCase("User")) {
            UserPanel userPanel = new UserPanel();
            userPanel.displayOptions();
        } else {
            System.out.println("Invalid role. Please restart the application.");
        }

        sc.close();
    }
}
