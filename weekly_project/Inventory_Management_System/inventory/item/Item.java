package inventory.item;

import java.util.*;

public class Item {
    private String name;
    private int id;
    private int quantity;
    private double price;
    private String category;
    private int reOrderLevel;
    private static int counterId = 1000;

    // Constructor
    public Item() {
        category = "General";
        reOrderLevel = 3;
        createItemWizard();
        id = counterId++;
    }

    public Item createItemWizard() {
        Scanner sc = new Scanner(System.in);
        Item newItem = new Item();

        System.out.println("Type 'quit' at any time to cancel.");

        // ----- MANDATORY FIELD: NAME ---
        while (true) {
            System.out.print("Enter Item Name (Mandatory): ");
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("quit"))
                return null; // Exit without saving
            if (!input.trim().isEmpty()) {// removing spaces
                newItem.setName(input);
                break; // Valid input, move to next
            }
            System.out.println("Error: Name cannot be empty!");
        }
        // ------ Price is mandatory field------
        while (true) {
            System.out.print("Enter Item Price (Mandatory): ");
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("quit"))
                return null; // Exit without saving
            if (!sc.hasNextInt()) {
                if (sc.next().equalsIgnoreCase("quit"))
                    return null;
                System.out.println("Error: Please enter a number.");
                continue;
            }
            newItem.setPrice(sc.nextInt());
            sc.nextLine();
            break;
        }

        // --- MANDATORY FIELD: QUANTITY ---
        while (true) {
            System.out.print("Enter Quantity: ");
            if (!sc.hasNextInt()) {
                if (sc.next().equalsIgnoreCase("quit"))
                    return null;
                System.out.println("Error: Please enter a number.");
                continue;
            }
            newItem.setQuantity(sc.nextInt());
            sc.nextLine(); // clear buffer
            break;
        }

        // --- OPTIONAL FIELD: CATEGORY ---
        System.out.print("Enter Category (Press Enter for default 'General'): ");
        String cat = sc.nextLine();
        if (cat.equalsIgnoreCase("quit"))
            return null;
        if (!cat.trim().isEmpty()) {
            newItem.setCategory(cat);
        }
        sc.clear();
        return newItem; // Finally return the finished object
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
    public void setCategory(String cat){
        category=cat;
    }
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String Category() {
        return category;
    }

    public boolean level() {
        return reOderLevel < quantity;
    }

    @Override
    public String toString() {
        return "Item{name='" + name + "', quantity=" + quantity + ", price=" + price + "category = " + category
                + "reOderLevel = " + reOderLevel + "}";
    }
};