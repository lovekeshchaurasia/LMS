package lms;

import java.util.Scanner;

public class UserPanel {
    public void displayOptions() {
        Scanner sc = new Scanner(System.in);
            System.out.println("\n--- User Panel ---");
            System.out.println("1. Search Books");
            System.out.println("2. Borrow a Book");
            System.out.println("3. Return a Book");
            System.out.println("4. View all books");
            System.out.println("5. Reserve a Book");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    Searching.searchBooks();
                    break;
                case 2:
//                borrowBook();
                    break;
                case 3:
//                returnBook();
                    break;
                case 4:
                    ViewBook.viewBooks();
                    break;
                case 5:
                    System.out.print("Enter user ID: ");
                    int userId = sc.nextInt();
                    System.out.print("Enter book ID to reserve: ");
                    int bookId = sc.nextInt();
                    ReserveBook.reserveBook(userId, bookId);
                default:
                    System.out.println("Invalid choice.");
            }

            sc.close();
        }
    }


