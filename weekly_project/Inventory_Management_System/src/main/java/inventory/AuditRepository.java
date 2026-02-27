package inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuditRepository {

    public void log(Integer actorUserId, String action, String targetType, Integer targetId, String details) {
        String sql = "INSERT INTO audit_logs(actor_user_id, action, target_type, target_id, details) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = Database.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            if (actorUserId == null) {
                statement.setNull(1, java.sql.Types.INTEGER);
            } else {
                statement.setInt(1, actorUserId);
            }
            statement.setString(2, action);
            statement.setString(3, targetType);
            if (targetId == null) {
                statement.setNull(4, java.sql.Types.INTEGER);
            } else {
                statement.setInt(4, targetId);
            }
            statement.setString(5, details);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to write audit log.", e);
        }
    }

    public List<AuditLogEntry> getAllLogs() {
        String sql = "SELECT id, actor_user_id, action, target_type, target_id, details, created_at FROM audit_logs ORDER BY id DESC";
        return runLogQuery(sql, null);
    }

    public List<AuditLogEntry> getTodayLogs() {
        String sql = "SELECT id, actor_user_id, action, target_type, target_id, details, created_at FROM audit_logs WHERE date(created_at) = date('now') ORDER BY id DESC";
        return runLogQuery(sql, null);
    }

    public List<AuditLogEntry> getLogsByActorUserId(int actorUserId) {
        String sql = "SELECT id, actor_user_id, action, target_type, target_id, details, created_at FROM audit_logs WHERE actor_user_id = ? ORDER BY id DESC";
        return runLogQuery(sql, actorUserId);
    }

    private List<AuditLogEntry> runLogQuery(String sql, Integer actorUserId) {
        List<AuditLogEntry> logs = new ArrayList<>();

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
                    Integer targetId = resultSet.getObject("target_id") == null
                            ? null
                            : resultSet.getInt("target_id");
                    logs.add(new AuditLogEntry(
                            resultSet.getInt("id"),
                            actorId,
                            resultSet.getString("action"),
                            resultSet.getString("target_type"),
                            targetId,
                            resultSet.getString("details"),
                            resultSet.getString("created_at")));
                }
            }
            return logs;
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to fetch audit logs.", e);
        }
    }
}
