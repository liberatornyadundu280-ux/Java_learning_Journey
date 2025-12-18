package inventory.items;

import java.util.*;
import inventory.item.Item;

public class Inventory<T> {
    private List<T> items;

    public Inventory() {
        items = new ArrayList<>();
    }

    // Add an item to the inventory
    public void addItem() {
        Item newItem = new Item();
        if (newItem != null && !check(newItem.getId)) {
            inventoryList.add(newItem);
            System.out.println("Item successfully saved to inventory!");
        } else {
            System.out.println(
                    check((newItem.getId) ? "item already in the inventory. Nothing is saved"
                            : "Entry cancelled. Nothing was saved."));
            return;
        }
        items.add(newItem);
    }

    public boolean check(int id) {
        for (T t : items) {
            if (t.getId == id)
                return true;
        }
        return false;
    }

    // Remove an item from the inventory
    public void removeItem(T item) {
        try {
            if (!items.contains(item)) {
                throw new IllegalArgumentException("Item not found in inventory.");
            }
            items.remove(item);
            System.out.println("Item removed successfully: " + item);
        } catch (Exception e) {
            System.out.println("Error removing item: " + e.getMessage());
        }
    }

    // View all items in the inventory
    public void viewItems() {
        if (items.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            System.out.println("Inventory items:");
            for (T item : items) {
                System.out.println(item);
            }
        }
    }

    // Getter method for items
    public List<T> getItems() {
        return items;
    }
}