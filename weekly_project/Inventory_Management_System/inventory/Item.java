package inventory;

import java.util.*;

public class Item {
    private String name;
    private int id;
    private int quantity;
    private double price;
    private String category;
    private int reStock;
    private static int counterId = 1000;

    // Constructor
    public Item() {
        category = "General";
        reStock = 3;
        id = counterId++;
    }

    /*
     * I created this method so as i can be able to take input without error and all
     * mandatory members are set here by force
     */
    public void createItemWizard(Scanner sc) {
        System.out.println("You type 'quit' at any time to cancel.");

        // ----- MANDATORY FIELD: NAME ---
        while (true) {
            System.out.print("Enter Item Name (Mandatory): ");
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("quit"))
                return; // Exit without saving
            if (!input.trim().isEmpty()) {// removing spaces
                this.name = input;
                break; // Valid input, move to next
            }
            System.out.println("Error: Name cannot be empty!");
        }

        // ------ Price is mandatory field------
        while (true) {
            System.out.print("Enter Item Price (Mandatory): ");
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("quit"))
                return; // Exit without saving
            try {
                price = Double.parseDouble(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a whole number." + e);
            }
        }

        // --- MANDATORY FIELD: QUANTITY ---
        while (true) {
            System.out.print("Enter Quantity: ");
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("quit"))
                return;
            try {
                this.quantity = Integer.parseInt(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a whole number." + e);
            }
        }

        // --- OPTIONAL FIELD: CATEGORY ---
        System.out.print("Enter Category (Press Enter for default 'General'): ");
        String cat = sc.nextLine();
        if (cat.equalsIgnoreCase("quit"))
            return;
        if (!cat.trim().isEmpty()) {
            category = cat;
        }
    }

    // Getter and setter methods
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCategory(String cat) {
        category = cat;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public boolean isStock() {
        return reStock < quantity;
    }

    /*
     * this methid is going to be called whena used wants to see the details of the
     * specific item called hence it overrides the already existing default toString
     */
    @Override
    public String toString() {
        return String.format("ID: %d | Name: %-10s | Price: %.2f | Qty: %d | Category: %-10s",
                id, name, price, quantity, category);
    }
};