import inventory.Inventory;

public class RemoveSmoke {
    public static void main(String[] args) {
        Inventory inv = new Inventory();
        int before = inv.getItems().size();
        inv.removeItem(1000);
        int after = inv.getItems().size();
        System.out.println("Before=" + before + ", After=" + after);
    }
}
