package inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    public User findById(int id) {
        String sql = "SELECT id, username, role, created_at FROM users WHERE id = ?";

        try (Connection connection = Database.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    return null;
                }
                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("role"),
                        resultSet.getString("created_at"));
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to fetch user by ID.", e);
        }
    }

    public User findByUsername(String username) {
        String sql = "SELECT id, username, role FROM users WHERE username = ?";

        try (Connection connection = Database.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    return null;
                }
                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("role"));
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to fetch user.", e);
        }
    }

    public String getPasswordHashByUsername(String username) {
        String sql = "SELECT password_hash FROM users WHERE username = ?";

        try (Connection connection = Database.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    return null;
                }
                return resultSet.getString("password_hash");
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to fetch password hash.", e);
        }
    }

    public boolean usernameExists(String username) {
        return findByUsername(username) != null;
    }

    public boolean usernameExistsExcludingUserId(String username, int userId) {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ? AND id <> ?";

        try (Connection connection = Database.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setInt(2, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next() && resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to check username uniqueness.", e);
        }
    }

    public boolean createUser(String username, String passwordHash, String role) {
        String sql = "INSERT INTO users(username, password_hash, role) VALUES (?, ?, ?)";

        try (Connection connection = Database.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, passwordHash);
            statement.setString(3, role);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to create user.", e);
        }
    }

    public List<User> getAllUsers() {
        String sql = "SELECT id, username, role, created_at FROM users ORDER BY id";
        List<User> users = new ArrayList<>();

        try (Connection connection = Database.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("role"),
                        resultSet.getString("created_at")));
            }
            return users;
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to fetch users.", e);
        }
    }

    public boolean updateUserDetails(int userId, String username, String role) {
        String sql = "UPDATE users SET username = ?, role = ? WHERE id = ?";

        try (Connection connection = Database.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, role);
            statement.setInt(3, userId);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to update user details.", e);
        }
    }

    public boolean updatePasswordHash(int userId, String passwordHash) {
        String sql = "UPDATE users SET password_hash = ? WHERE id = ?";

        try (Connection connection = Database.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, passwordHash);
            statement.setInt(2, userId);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to update password hash.", e);
        }
    }

    public int countAdmins() {
        String sql = "SELECT COUNT(*) FROM users WHERE role = 'ADMIN'";

        try (Connection connection = Database.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            return resultSet.next() ? resultSet.getInt(1) : 0;
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to count admin users.", e);
        }
    }

    public boolean deleteUser(int userId) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection connection = Database.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to delete user.", e);
        }
    }

}
