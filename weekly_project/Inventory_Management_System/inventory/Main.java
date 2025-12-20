package inventory;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Use a single Scanner for the whole app to avoid buffer issues
        Scanner scanner = new Scanner(System.in);
        Inventory myInventory = new Inventory(); // Pass scanner to manager

        boolean exit = false;

        System.out.println("\n\n\n==========================================");
        System.out.println("   QUANTUM STOCK - INVENTORY SYSTEM v1.0  ");
        System.out.println("==========================================");

        while (!exit) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. [NEW] Add Product");
            System.out.println("2. [DEL] Remove Product");
            System.out.println("3. [LST] View All Inventory");
            System.out.println("4. [SRCH] Find Product by ID");
            System.out.println("5. [RPT] Low Stock Alert");
            System.out.println("6. Exit System");
            System.out.print("\nSelect Action: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    Item newItem = new Item();
                    newItem.createItemWizard(scanner);
                    myInventory.addItem(newItem);
                    break;
                case "2":
                    int id = InputValidator.getValidInt("Enter item id to remove: ");
                    myInventory.removeItem(id);
                    break;
                case "3":
                    myInventory.viewItems();
                    break;
                case "4":
                    myInventory.findItem(myInventory, scanner);
                    break;
                case "5":
                    myInventory.checkLowStock();
                    break;
                case "6":
                    System.out.println("Closing system... Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println(">> Invalid selection. Please choose 1-6.");
            }
        }
        scanner.close();
    }
}