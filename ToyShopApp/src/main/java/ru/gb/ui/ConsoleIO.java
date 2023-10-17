package ru.gb.ui;

import ru.gb.entities.StorageCell;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ConsoleIO {
    public void printMainMenu() {
        System.out.println("Welcome to your shop!\n\nProducts are already on stock\n1. See your stocks\n2. Start sale in your shop\n3. Exit");
    }

    public String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void printMessage(String message) {
        System.out.print(message);
    }

    public void printStock(List<StorageCell> storage) {
        for (StorageCell storageCell : storage) {
            System.out.printf("%s\nQuantity: %d\n\n", storageCell.getProduct(), storageCell.getQuantity());
        }
    }

    public void printSaleMenu() {
        System.out.println("Welcome to sale event!\n\n1. Set products weight\n2. Start sale\n3. Exit to Main Menu");
    }

    public Integer getIntFromUser() {

        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                int userInput = scanner.nextInt();
                if (userInput <= 100 && userInput > 0) {
                    return userInput;
                }
                System.out.print("Your value not in frames from 1 to 100! Please try again: ");
            } catch (NoSuchElementException | IllegalStateException exception) {
                System.out.print("Error, Not a valid input! Please try again: ");
            }
        }
    }

    public void printReleaseMenu() {
        System.out.println("\n\n1. Release product\n2. Exit to Main Menu");
    }
}
