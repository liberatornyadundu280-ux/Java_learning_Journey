package inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemRepository {

    public boolean addItem(Item item) {
        String sql = "INSERT INTO items(name, price, quantity, category, restock_level) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = Database.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, item.getName());
            statement.setDouble(2, item.getPrice());
            statement.setInt(3, item.getQuantity());
            statement.setString(4, item.getCategory());
            statement.setInt(5, item.getRestockLevel());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected != 1) {
                return false;
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    item.setId(generatedKeys.getInt(1));
                }
            }
            return true;
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to add item.", e);
        }
    }

    public Item removeItemById(int id) {
        Item existing = findById(id);
        if (existing == null) {
            return null;
        }

        String sql = "DELETE FROM items WHERE id = ?";

        try (Connection connection = Database.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return existing;
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to remove item with ID: " + id, e);
        }
    }

    public List<Item> getAllItems() {
        return runQuery("SELECT id, name, quantity, price, category, restock_level FROM items ORDER BY id");
    }

    public Item findById(int id) {
        String sql = "SELECT id, name, quantity, price, category, restock_level FROM items WHERE id = ?";

        try (Connection connection = Database.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapRow(resultSet);
                }
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to fetch item by ID.", e);
        }
    }

    public List<Item> searchByName(String name) {
        String sql = "SELECT id, name, quantity, price, category, restock_level FROM items WHERE LOWER(name) LIKE ? ORDER BY id";
        return runQueryWithSingleParameter(sql, "%" + name.toLowerCase() + "%");
    }

    public List<Item> searchByCategory(String category) {
        String sql = "SELECT id, name, quantity, price, category, restock_level FROM items WHERE LOWER(category) = ? ORDER BY id";
        return runQueryWithSingleParameter(sql, category.toLowerCase());
    }

    public List<Item> searchByPriceRange(double minPrice, double maxPrice) {
        String sql = "SELECT id, name, quantity, price, category, restock_level FROM items WHERE price BETWEEN ? AND ? ORDER BY id";
        List<Item> items = new ArrayList<>();

        try (Connection connection = Database.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, minPrice);
            statement.setDouble(2, maxPrice);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    items.add(mapRow(resultSet));
                }
            }
            return items;
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to search items by price range.", e);
        }
    }

    public List<Item> getLowStockItems() {
        String sql = "SELECT id, name, quantity, price, category, restock_level FROM items WHERE quantity <= restock_level ORDER BY id";
        return runQuery(sql);
    }

    public List<InventoryTransactionEntry> getAllInventoryTransactions() {
        String sql = "SELECT id, item_id, transaction_type, quantity_change, quantity_before, quantity_after, note, actor_user_id, created_at FROM inventory_transactions ORDER BY id DESC";
        return runTransactionQuery(sql, null);
    }

    public List<InventoryTransactionEntry> getTodayInventoryTransactions() {
        String sql = "SELECT id, item_id, transaction_type, quantity_change, quantity_before, quantity_after, note, actor_user_id, created_at FROM inventory_transactions WHERE date(created_at, 'localtime') = date('now', 'localtime') ORDER BY id DESC";
        return runTransactionQuery(sql, null);
    }

    public List<InventoryTransactionEntry> getInventoryTransactionsByActorUserId(int actorUserId) {
        String sql = "SELECT id, item_id, transaction_type, quantity_change, quantity_before, quantity_after, note, actor_user_id, created_at FROM inventory_transactions WHERE actor_user_id = ? ORDER BY id DESC";
        return runTransactionQuery(sql, actorUserId);
    }

    public boolean updateStockQuantity(int itemId, int newQuantity, int actorUserId) {
        String selectSql = "SELECT quantity FROM items WHERE id = ?";
        String updateSql = "UPDATE items SET quantity = ? WHERE id = ?";
        String txnSql = "INSERT INTO inventory_transactions(item_id, transaction_type, quantity_change, quantity_before, quantity_after, note, actor_user_id) VALUES (?, 'ADJUSTMENT', ?, ?, ?, ?, ?)";
        String auditSql = "INSERT INTO audit_logs(actor_user_id, action, target_type, target_id, details) VALUES (?, 'STOCK_EDIT', 'ITEM', ?, ?)";

        try (Connection connection = Database.getConnection()) {
            connection.setAutoCommit(false);
            try {
                int beforeQty;
                try (PreparedStatement selectStatement = connection.prepareStatement(selectSql)) {
                    selectStatement.setInt(1, itemId);
                    try (ResultSet rs = selectStatement.executeQuery()) {
                        if (!rs.next()) {
                            connection.rollback();
                            return false;
                        }
                        beforeQty = rs.getInt(1);
                    }
                }

                try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
                    updateStatement.setInt(1, newQuantity);
                    updateStatement.setInt(2, itemId);
                    if (updateStatement.executeUpdate() != 1) {
                        connection.rollback();
                        return false;
                    }
                }

                try (PreparedStatement txnStatement = connection.prepareStatement(txnSql)) {
                    txnStatement.setInt(1, itemId);
                    txnStatement.setInt(2, newQuantity - beforeQty);
                    txnStatement.setInt(3, beforeQty);
                    txnStatement.setInt(4, newQuantity);
                    txnStatement.setString(5, "Direct stock adjustment");
                    txnStatement.setInt(6, actorUserId);
                    txnStatement.executeUpdate();
                }

                try (PreparedStatement auditStatement = connection.prepareStatement(auditSql)) {
                    auditStatement.setInt(1, actorUserId);
                    auditStatement.setInt(2, itemId);
                    auditStatement.setString(3, "before=" + beforeQty + ", after=" + newQuantity);
                    auditStatement.executeUpdate();
                }

                connection.commit();
                return true;
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to update stock quantity.", e);
        }
    }

    public boolean decrementStockForSale(int itemId, int soldQuantity, int actorUserId) {
        String selectSql = "SELECT quantity FROM items WHERE id = ?";
        String updateSql = "UPDATE items SET quantity = quantity - ? WHERE id = ? AND quantity >= ?";
        String txnSql = "INSERT INTO inventory_transactions(item_id, transaction_type, quantity_change, quantity_before, quantity_after, note, actor_user_id) VALUES (?, 'SALE', ?, ?, ?, ?, ?)";
        String auditSql = "INSERT INTO audit_logs(actor_user_id, action, target_type, target_id, details) VALUES (?, 'SALE_RECORDED', 'ITEM', ?, ?)";

        try (Connection connection = Database.getConnection()) {
            connection.setAutoCommit(false);
            try {
                int beforeQty;
                try (PreparedStatement selectStatement = connection.prepareStatement(selectSql)) {
                    selectStatement.setInt(1, itemId);
                    try (ResultSet rs = selectStatement.executeQuery()) {
                        if (!rs.next()) {
                            connection.rollback();
                            return false;
                        }
                        beforeQty = rs.getInt(1);
                    }
                }

                int afterQty = beforeQty - soldQuantity;
                if (afterQty < 0) {
                    connection.rollback();
                    return false;
                }

                try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
                    updateStatement.setInt(1, soldQuantity);
                    updateStatement.setInt(2, itemId);
                    updateStatement.setInt(3, soldQuantity);
                    if (updateStatement.executeUpdate() != 1) {
                        connection.rollback();
                        return false;
                    }
                }

                try (PreparedStatement txnStatement = connection.prepareStatement(txnSql)) {
                    txnStatement.setInt(1, itemId);
                    txnStatement.setInt(2, -soldQuantity);
                    txnStatement.setInt(3, beforeQty);
                    txnStatement.setInt(4, afterQty);
                    txnStatement.setString(5, "Sale transaction");
                    txnStatement.setInt(6, actorUserId);
                    txnStatement.executeUpdate();
                }

                try (PreparedStatement auditStatement = connection.prepareStatement(auditSql)) {
                    auditStatement.setInt(1, actorUserId);
                    auditStatement.setInt(2, itemId);
                    auditStatement.setString(3, "sold=" + soldQuantity + ", before=" + beforeQty + ", after=" + afterQty);
                    auditStatement.executeUpdate();
                }

                connection.commit();
                return true;
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to record sale.", e);
        }
    }

    private List<Item> runQueryWithSingleParameter(String sql, String value) {
        List<Item> items = new ArrayList<>();

        try (Connection connection = Database.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, value);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    items.add(mapRow(resultSet));
                }
            }
            return items;
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to execute filtered query.", e);
        }
    }

    private List<Item> runQuery(String sql) {
        List<Item> items = new ArrayList<>();

        try (Connection connection = Database.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                items.add(mapRow(resultSet));
            }
            return items;
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to execute query.", e);
        }
    }

    private Item mapRow(ResultSet resultSet) throws SQLException {
        return new Item(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getInt("quantity"),
                resultSet.getDouble("price"),
                resultSet.getString("category"),
                resultSet.getInt("restock_level"));
    }

    private List<InventoryTransactionEntry> runTransactionQuery(String sql, Integer actorUserId) {
        List<InventoryTransactionEntry> transactions = new ArrayList<>();

        try (Connection connection = Database.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            if (actorUserId != null) {
                statement.setInt(1, actorUserId);
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Integer actorId = resultSet.getObject("actor_user_id") == null
                            ? null
                            : resultSet.getInt("actor_user_id");
                    transactions.add(new InventoryTransactionEntry(
                            resultSet.getInt("id"),
                            resultSet.getInt("item_id"),
                            resultSet.getString("transaction_type"),
                            resultSet.getInt("quantity_change"),
                            resultSet.getInt("quantity_before"),
                            resultSet.getInt("quantity_after"),
                            resultSet.getString("note"),
                            actorId,
                            resultSet.getString("created_at")));
                }
            }
            return transactions;
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to fetch inventory transactions.", e);
        }
    }
}
