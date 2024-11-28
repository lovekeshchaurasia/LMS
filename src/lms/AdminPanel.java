package lms;

import java.util.Scanner;

public class AdminPanel {
    public void displayOptions() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n--- Admin Panel ---");
        System.out.println("1. Add a Book");
        System.out.println("2. Add a User");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                AddBooks.addBook();
                break;
            case 2:
                AddUser.addUser();
                break;
            default:
                System.out.println("Invalid choice.");
        }

        sc.close();
    }
}

