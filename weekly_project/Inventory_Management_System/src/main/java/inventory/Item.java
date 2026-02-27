package inventory;

import java.util.Scanner;

public class Item {
    private int id;
    private String name;
    private int quantity;
    private double price;
    private String category;
    private int restockLevel;

    public Item() {
        this.id = 0;
        this.category = "General";
        this.restockLevel = 3;
    }

    public Item(int id, String name, int quantity, double price, String category, int restockLevel) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
        this.restockLevel = restockLevel;
    }

    public boolean createItemWizard(Scanner sc) {
        System.out.println("You type 'quit' at any time to cancel.");

        while (true) {
            System.out.print("Enter Item Name (Mandatory): ");
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("quit")) {
                return false;
            }
            if (!input.trim().isEmpty()) {
                this.name = input;
                break;
            }
            System.out.println("Error: Name cannot be empty!");
        }

        while (true) {
            System.out.print("Enter Item Price (Mandatory): ");
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("quit")) {
                return false;
            }
            try {
                double parsed = Double.parseDouble(input);
                if (parsed < 0) {
                    System.out.println("Error: Price cannot be negative.");
                    continue;
                }
                this.price = parsed;
                break;
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number.");
            }
        }

        while (true) {
            System.out.print("Enter Quantity: ");
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("quit")) {
                return false;
            }
            try {
                int parsed = Integer.parseInt(input);
                if (parsed < 0) {
                    System.out.println("Error: Quantity cannot be negative.");
                    continue;
                }
                this.quantity = parsed;
                break;
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a whole number.");
            }
        }

        System.out.print("Enter Category (Press Enter for default 'General'): ");
        String cat = sc.nextLine();
        if (cat.equalsIgnoreCase("quit")) {
            return false;
        }
        if (!cat.trim().isEmpty()) {
            category = cat;
        }

        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getRestockLevel() {
        return restockLevel;
    }

    public void setRestockLevel(int restockLevel) {
        this.restockLevel = restockLevel;
    }

    public boolean isLowStock() {
        return quantity <= restockLevel;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %-15s | Price: %.2f | Qty: %d | Category: %-12s",
                id, name, price, quantity, category);
    }
}
