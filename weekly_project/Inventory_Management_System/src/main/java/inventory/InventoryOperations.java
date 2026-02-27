package inventory;

import java.util.Scanner;

public interface InventoryOperations {
    public void addItem(Item item);

    public void removeItem(int id);

    public void viewItems();

    public void checkLowStock();

    void findItem(Scanner sc);
}
