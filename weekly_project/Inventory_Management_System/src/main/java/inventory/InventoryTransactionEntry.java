package inventory;

public class InventoryTransactionEntry {
    private final int id;
    private final int itemId;
    private final String transactionType;
    private final int quantityChange;
    private final int quantityBefore;
    private final int quantityAfter;
    private final String note;
    private final Integer actorUserId;
    private final String createdAt;

    public InventoryTransactionEntry(int id, int itemId, String transactionType, int quantityChange,
            int quantityBefore, int quantityAfter, String note, Integer actorUserId, String createdAt) {
        this.id = id;
        this.itemId = itemId;
        this.transactionType = transactionType;
        this.quantityChange = quantityChange;
        this.quantityBefore = quantityBefore;
        this.quantityAfter = quantityAfter;
        this.note = note;
        this.actorUserId = actorUserId;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public int getItemId() {
        return itemId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public int getQuantityChange() {
        return quantityChange;
    }

    public int getQuantityBefore() {
        return quantityBefore;
    }

    public int getQuantityAfter() {
        return quantityAfter;
    }

    public String getNote() {
        return note;
    }

    public Integer getActorUserId() {
        return actorUserId;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
