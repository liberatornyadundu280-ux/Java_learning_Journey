package inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inventory implements InventoryOperations {
    private final ItemRepository itemRepository;

    public Inventory() {
        DatabaseInitializer.initialize();
        this.itemRepository = new ItemRepository();
    }

    @Override
    public void addItem(Item newItem) {
        if (newItem == null) {
            System.out.println("Entry cancelled. Nothing was saved.");
            return;
        }

        boolean inserted = itemRepository.addItem(newItem);
        if (inserted) {
            System.out.println("Item successfully saved to inventory!");
        } else {
            System.out.println("Failed to save item.");
        }
    }

    @Override
    public void removeItem(int id) {
        Item removed = itemRepository.removeItemById(id);
        if (removed != null) {
            System.out.println("Item removed successfully: " + removed);
            return;
        }

        System.out.println("Error removing item: Item not found in inventory.");
    }

    @Override
    public void viewItems() {
        List<Item> items = itemRepository.getAllItems();
        if (items.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        System.out.println("Inventory items:");
        for (Item item : items) {
            System.out.println(item);
        }
    }

    @Override
    public void findItem(Scanner sc) {
        System.out.println("\n--- SEARCH BY ---");
        System.out.println("a. ID | b. Name | c. Category | d. Price Range");
        System.out.print("Choose option: ");
        String subChoice = sc.nextLine().toLowerCase();

        switch (subChoice) {
            case "a": {
                int id = InputValidator.getValidInt(sc, "Enter ID: ");
                Item result = itemRepository.findById(id);
                List<Item> results = new ArrayList<>();
                if (result != null) {
                    results.add(result);
                }
                displaySearchResults(results);
                break;
            }
            case "b": {
                System.out.print("Enter Name: ");
                String name = sc.nextLine();
                displaySearchResults(itemRepository.searchByName(name));
                break;
            }
            case "c": {
                System.out.print("Enter Category: ");
                String category = sc.nextLine();
                displaySearchResults(itemRepository.searchByCategory(category));
                break;
            }
            case "d": {
                double min = InputValidator.getValidDouble(sc, "Enter Min Price: ", 0.0);
                double max = InputValidator.getValidDouble(sc, "Enter Max Price: ", min);
                displaySearchResults(itemRepository.searchByPriceRange(min, max));
                break;
            }
            default:
                System.out.println("Invalid search option.");
        }
    }

    @Override
    public void checkLowStock() {
        List<Item> lowStockItems = itemRepository.getLowStockItems();
        if (lowStockItems.isEmpty()) {
            System.out.println("Seems like nothing needs restocking");
            return;
        }

        System.out.println("Inventory items with low stock:");
        for (Item item : lowStockItems) {
            System.out.println(item);
        }
    }

    @Override
    public void editStockQuantity(int itemId, int newQuantity, int actorUserId) {
        if (newQuantity < 0) {
            System.out.println("Stock cannot be negative.");
            return;
        }

        Item item = itemRepository.findById(itemId);
        if (item == null) {
            System.out.println("Item not found.");
            return;
        }

        boolean updated = itemRepository.updateStockQuantity(itemId, newQuantity, actorUserId);
        if (updated) {
            System.out.println("Stock updated successfully for item " + item.getName() + ". New qty: " + newQuantity);
        } else {
            System.out.println("Could not update stock.");
        }
    }

    @Override
    public void recordSale(int itemId, int soldQuantity, int actorUserId) {
        if (soldQuantity <= 0) {
            System.out.println("Sold quantity must be at least 1.");
            return;
        }

        Item item = itemRepository.findById(itemId);
        if (item == null) {
            System.out.println("Item not found.");
            return;
        }

        boolean updated = itemRepository.decrementStockForSale(itemId, soldQuantity, actorUserId);
        if (updated) {
            Item refreshed = itemRepository.findById(itemId);
            int remaining = refreshed == null ? -1 : refreshed.getQuantity();
            System.out.println("Sale recorded for " + item.getName() + ". Qty sold: " + soldQuantity
                    + " | Remaining: " + remaining);
        } else {
            System.out.println("Sale failed. Not enough stock available.");
        }
    }

    private void displaySearchResults(List<Item> results) {
        if (results == null || results.isEmpty()) {
            System.out.println("\n[!] No matching items found in the system.");
            return;
        }

        System.out.println("\n--- SEARCH RESULTS ---");
        for (Item item : results) {
            System.out.println(item);
        }
        System.out.println("----------------------");
    }

    public List<Item> getItems() {
        return itemRepository.getAllItems();
    }
}
