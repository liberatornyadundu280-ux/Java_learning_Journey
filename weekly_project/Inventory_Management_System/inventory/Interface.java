package inventory;

import java.util.Scanner;

public interface Interface {
    public void addItem(Item item);

    public void removeItem(int id);

    public void viewItems();

    public void checkLowStock();

    void findItem(Inventory inventory, Scanner sc);
}