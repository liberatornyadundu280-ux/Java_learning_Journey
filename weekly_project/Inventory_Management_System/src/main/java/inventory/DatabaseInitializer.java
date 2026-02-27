package inventory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class DatabaseInitializer {
    private static boolean initialized = false;

    private DatabaseInitializer() {
    }

    public static synchronized void initialize() {
        if (initialized) {
            return;
        }

        ensureDataDirectory();

        try (Connection connection = Database.getConnection()) {
            executeSqlScript(connection, "db/schema.sql");

            if (isTableEmpty(connection, "items")) {
                executeSqlScript(connection, "db/seed.sql");
            }
            ensureDefaultAdminAccount(connection);

            initialized = true;
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to initialize database.", e);
        }
    }

    static synchronized void resetForTests() {
        initialized = false;
    }

    private static void ensureDataDirectory() {
        String dbUrl = Database.resolveDbUrl();
        if (!dbUrl.startsWith("jdbc:sqlite:")) {
            return;
        }

        String pathText = dbUrl.substring("jdbc:sqlite:".length());
        Path path = Path.of(pathText);
        Path parent = path.getParent();
        if (parent == null) {
            return;
        }

        try {
            Files.createDirectories(parent);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to create data directory: " + parent, e);
        }
    }

    private static boolean isTableEmpty(Connection connection, String tableName) throws SQLException {
        String sql = "SELECT COUNT(*) FROM " + tableName;
        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {
            return resultSet.next() && resultSet.getInt(1) == 0;
        }
    }

    private static void ensureDefaultAdminAccount(Connection connection) throws SQLException {
        String selectSql = "SELECT id, password_hash FROM users WHERE username = ?";
        try (PreparedStatement selectStatement = connection.prepareStatement(selectSql)) {
            selectStatement.setString(1, "admin");
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (!resultSet.next()) {
                    insertDefaultAdmin(connection);
                    return;
                }

                String passwordHash = resultSet.getString("password_hash");
                if (passwordHash != null && passwordHash.startsWith("TODO_")) {
                    updateDefaultAdminPassword(connection);
                }
            }
        }
    }

    private static void insertDefaultAdmin(Connection connection) throws SQLException {
        String insertSql = "INSERT INTO users(username, password_hash, role) VALUES (?, ?, ?)";
        try (PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {
            insertStatement.setString(1, "admin");
            insertStatement.setString(2, PasswordUtil.hashPassword("admin123"));
            insertStatement.setString(3, "ADMIN");
            insertStatement.executeUpdate();
        }
    }

    private static void updateDefaultAdminPassword(Connection connection) throws SQLException {
        String updateSql = "UPDATE users SET password_hash = ?, role = 'ADMIN' WHERE username = ?";
        try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
            updateStatement.setString(1, PasswordUtil.hashPassword("admin123"));
            updateStatement.setString(2, "admin");
            updateStatement.executeUpdate();
        }
    }

    private static void executeSqlScript(Connection connection, String resourcePath) {
        String sqlText = loadResourceText(resourcePath);
        String[] statements = sqlText.split(";");

        for (String statementText : statements) {
            String trimmed = statementText.trim();
            if (trimmed.isEmpty()) {
                continue;
            }

            try (Statement statement = connection.createStatement()) {
                statement.execute(trimmed);
            } catch (SQLException e) {
                throw new IllegalStateException("Failed executing SQL statement: " + trimmed, e);
            }
        }
    }

    private static String loadResourceText(String resourcePath) {
        InputStream stream = DatabaseInitializer.class.getClassLoader().getResourceAsStream(resourcePath);
        if (stream == null) {
            throw new IllegalStateException("Missing SQL resource: " + resourcePath);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append('\n');
            }
            return builder.toString();
        } catch (IOException e) {
            throw new IllegalStateException("Failed reading SQL resource: " + resourcePath, e);
        }
    }
}
