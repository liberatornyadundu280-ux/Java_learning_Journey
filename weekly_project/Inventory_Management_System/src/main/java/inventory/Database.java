package inventory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Database {
    private static final String DEFAULT_DB_URL = "jdbc:sqlite:data/inventory.db";

    private Database() {
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(resolveDbUrl());
        try (var statement = connection.createStatement()) {
            // SQLite keeps foreign key enforcement disabled unless enabled per connection.
            statement.execute("PRAGMA foreign_keys = ON");
        }
        return connection;
    }

    public static String resolveDbUrl() {
        String dbUrlFromProperty = System.getProperty("IMS_DB_URL");
        if (dbUrlFromProperty != null && !dbUrlFromProperty.trim().isEmpty()) {
            return dbUrlFromProperty.trim();
        }

        String dbUrl = System.getenv("IMS_DB_URL");
        if (dbUrl == null || dbUrl.trim().isEmpty()) {
            return DEFAULT_DB_URL;
        }
        return dbUrl.trim();
    }
}
