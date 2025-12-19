package inventory;

import java.util.*;

public class Inventory<T> implements Interface {
    private List<T> items;

    public Inventory() {
        items = new ArrayList<>();
    }

    // Add an item to the inventory
    @Override
    public void addItem() {
        Item newItem = new Item();
        if (newItem != null && !check(newItem.getId)) {
            items.add(newItem);
            System.out.println("Item successfully saved to inventory!");
        } else {
            System.out.println(
                    check((newItem.getId) ? "item already in the inventory. Nothing is saved"
                            : "Entry cancelled. Nothing was saved."));
            return;
        }
    }

    // we are checking the existence of the id/ item
    public boolean check(int id) {
        for (T t : items) {
            if (t.getId == id)
                return true;
        }
        return false;
    }

    // Remove an item from the inventory
    @Override
    public void removeItem(int id) {
        for (T t : items) {
            if (t.getId == id) {
                Item item = t;
                items.remove(t);
                System.out.println("Item removed successfully: " + item);
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
            for (T item : items) {
                System.out.println(item.toString());
            }
        }
    }

    // Getter method for items
    public List<T> getItems() {
        return items;
    }
}