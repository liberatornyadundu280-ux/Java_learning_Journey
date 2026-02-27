package inventory;

public class User {
    private final int id;
    private final String username;
    private final String role;
    private final String createdAt;

    public User(int id, String username, String role) {
        this(id, username, role, null);
    }

    public User(int id, String username, String role, String createdAt) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
