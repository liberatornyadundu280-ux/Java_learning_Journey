package inventory;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Inventory implements Interface {
    private List<Item> items;

    public Inventory() {
        items = new ArrayList<>();
    }

    // Add an item to the inventory
    @Override
    public void addItem(Item newItem) {
        if (newItem != null && !check(newItem.getId())) {
            items.add(newItem);
            System.out.println("Item successfully saved to inventory!");
        } else {
            System.out.println(
                    check(newItem.getId()) ? "item already in the inventory. Nothing is saved"
                            : "Entry cancelled. Nothing was saved.");
            return;
        }
    }

    // we are checking the existence of the id/ item
    public boolean check(int id) {
        for (Item t : items) {
            if (t.getId() == id)
                return true;
        }
        return false;
    }

    // Remove an item from the inventory
    @Override
    public void removeItem(int id) {
        for (Item t : items) {
            if (t.getId() == id) {
                Item item = t;
                items.remove(t);
                System.out.println("Item removed successfully: " + item);
                return;
            }
        }
        System.out.println("Error removing item: Item not found in inventory.  ");

    }

    // View all items in the inventory
    @Override
    public void viewItems() {
        if (items.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            System.out.println("Inventory items:");
            for (Item item : items) {
                System.out.println(item.toString());
            }
        }
    }

    // You can use this for names, prices, or IDs
    @Override
    public void findItem(Inventory inventory, Scanner sc) {
        System.out.println("\n--- SEARCH BY ---");
        System.out.println("a. ID | b. Name | c. Category | d. Price Range");
        System.out.print("Choose option: ");
        String subChoice = sc.nextLine().toLowerCase();

        switch (subChoice) {
            case "a":
                System.out.print("Enter ID: ");
                int id = Integer.parseInt(sc.nextLine());
                // We pass the rule: "Item ID must match input ID"
                inventory.displaySearchResults(inventory.search(item -> item.getId() == id));
                break;

            case "b":
                System.out.print("Enter Name: ");
                String name = sc.nextLine();
                // Rule: "Item name must contain the search string"
                inventory.displaySearchResults(
                        inventory.search(item -> item.getName().toLowerCase().contains(name.toLowerCase())));
                break;

            case "c":
                System.out.print("Enter Category: ");
                String cat = sc.nextLine();
                inventory.displaySearchResults(inventory.search(item -> item.getCategory().equalsIgnoreCase(cat)));
                break;

            case "d":
                System.out.print("Enter Min Price: ");
                double min = Double.parseDouble(sc.nextLine());
                System.out.print("Enter Max Price: ");
                double max = Double.parseDouble(sc.nextLine());
                // Rule: "Price must be between min and max"
                inventory.displaySearchResults(
                        inventory.search(item -> item.getPrice() >= min && item.getPrice() <= max));
                break;

            default:
                System.out.println("Invalid search option.");
        }
    }

    public void displaySearchResults(List<Item> results) {
        if (results == null || results.isEmpty()) {
            System.out.println("\n[!] No matching items found in the system.");
        } else {
            System.out.println("\n--- SEARCH RESULTS ---");
            for (Item item : results) {
                System.out.println(item); // This calls your Item.toString()
            }
            System.out.println("----------------------");
        }
    }

    public List<Item> search(Predicate<Item> criteria) {
        return items.stream()
                .filter(criteria)
                .collect(Collectors.toList());
    }

    @Override
    public void checkLowStock() {
        if (items.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        } else {
            System.out.println("Inventory items with low stock:");
            boolean f = true;
            for (Item item : items) {
                if (item.isStock()) {
                    System.out.println(item.toString());
                    f = false;
                }
            }
            if (f) {
                System.out.println("Seems like nothing needs restocking");
            }
        }
    }

    // Getter method for items
    public List<Item> getItems() {
        return items;
    }
}