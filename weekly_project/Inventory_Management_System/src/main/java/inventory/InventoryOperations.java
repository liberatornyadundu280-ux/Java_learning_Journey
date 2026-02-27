package inventory;

import java.util.Scanner;

public interface InventoryOperations {
    public void addItem(Item item);

    public void removeItem(int id);

    public void viewItems();

    public void checkLowStock();

    void findItem(Scanner sc);

    void editStockQuantity(int itemId, int newQuantity, int actorUserId);

    void recordSale(int itemId, int soldQuantity, int actorUserId);
}
