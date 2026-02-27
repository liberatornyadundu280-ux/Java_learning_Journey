package inventory;

public class AuditLogEntry {
    private final int id;
    private final Integer actorUserId;
    private final String action;
    private final String targetType;
    private final Integer targetId;
    private final String details;
    private final String createdAt;

    public AuditLogEntry(int id, Integer actorUserId, String action, String targetType,
            Integer targetId, String details, String createdAt) {
        this.id = id;
        this.actorUserId = actorUserId;
        this.action = action;
        this.targetType = targetType;
        this.targetId = targetId;
        this.details = details;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public Integer getActorUserId() {
        return actorUserId;
    }

    public String getAction() {
        return action;
    }

    public String getTargetType() {
        return targetType;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public String getDetails() {
        return details;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
