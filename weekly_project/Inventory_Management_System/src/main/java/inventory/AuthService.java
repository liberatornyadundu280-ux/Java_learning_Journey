package inventory;

import java.util.List;

public class AuthService {
    private final UserRepository userRepository;
    private final AuditRepository auditRepository;

    public AuthService() {
        this.userRepository = new UserRepository();
        this.auditRepository = new AuditRepository();
    }

    public User authenticate(String username, String rawPassword) {
        String storedHash = userRepository.getPasswordHashByUsername(username);
        if (storedHash == null) {
            auditRepository.log(null, "LOGIN_FAILED", "USER", null, "username=" + username);
            return null;
        }

        boolean valid = PasswordUtil.verifyPassword(rawPassword, storedHash);
        if (!valid) {
            User knownUser = userRepository.findByUsername(username);
            Integer actorId = knownUser == null ? null : knownUser.getId();
            auditRepository.log(actorId, "LOGIN_FAILED", "USER", actorId, "username=" + username);
            return null;
        }

        User authenticatedUser = userRepository.findByUsername(username);
        auditRepository.log(
                authenticatedUser.getId(),
                "LOGIN_SUCCESS",
                "USER",
                authenticatedUser.getId(),
                "username=" + authenticatedUser.getUsername());
        return authenticatedUser;
    }

    public boolean createUser(int actorUserId, String username, String rawPassword, String role) {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        if (rawPassword == null || rawPassword.length() < 6) {
            return false;
        }
        if (!isValidRole(role)) {
            return false;
        }

        String normalizedUsername = username.trim();
        String normalizedRole = role.trim().toUpperCase();
        if (userRepository.usernameExists(normalizedUsername)) {
            return false;
        }

        String passwordHash = PasswordUtil.hashPassword(rawPassword);
        boolean created = userRepository.createUser(normalizedUsername, passwordHash, normalizedRole);
        if (created) {
            User createdUser = userRepository.findByUsername(normalizedUsername);
            Integer targetId = createdUser == null ? null : createdUser.getId();
            auditRepository.log(actorUserId, "USER_CREATE", "USER", targetId,
                    "role=" + normalizedRole + ", username=" + normalizedUsername);
        }
        return created;
    }

    public boolean createStaffUser(int actorUserId, String username, String rawPassword) {
        return createUser(actorUserId, username, rawPassword, "STAFF");
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public User getUserById(int userId) {
        return userRepository.findById(userId);
    }

    public boolean updateUserDetails(int actorUserId, int userId, String username, String role) {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        if (!isValidRole(role)) {
            return false;
        }

        String normalizedUsername = username.trim();
        String normalizedRole = role.trim().toUpperCase();

        User existingUser = userRepository.findById(userId);
        if (existingUser == null) {
            return false;
        }
        if (userRepository.usernameExistsExcludingUserId(normalizedUsername, userId)) {
            return false;
        }

        boolean updated = userRepository.updateUserDetails(userId, normalizedUsername, normalizedRole);
        if (updated) {
            auditRepository.log(
                    actorUserId,
                    "USER_UPDATE",
                    "USER",
                    userId,
                    "username=" + normalizedUsername + ", role=" + normalizedRole);
        }
        return updated;
    }

    public boolean resetUserPassword(int actorUserId, int userId, String newRawPassword) {
        if (newRawPassword == null || newRawPassword.length() < 6) {
            return false;
        }

        User existingUser = userRepository.findById(userId);
        if (existingUser == null) {
            return false;
        }

        String newHash = PasswordUtil.hashPassword(newRawPassword);
        boolean updated = userRepository.updatePasswordHash(userId, newHash);
        if (updated) {
            auditRepository.log(actorUserId, "USER_PASSWORD_RESET", "USER", userId, "admin_reset=true");
        }
        return updated;
    }

    public boolean changeOwnPassword(int userId, String username, String currentRawPassword, String newRawPassword) {
        if (newRawPassword == null || newRawPassword.length() < 6) {
            return false;
        }

        User user = userRepository.findById(userId);
        if (user == null) {
            return false;
        }
        if (!user.getUsername().equals(username)) {
            return false;
        }

        String storedHash = userRepository.getPasswordHashByUsername(username);
        if (storedHash == null || !PasswordUtil.verifyPassword(currentRawPassword, storedHash)) {
            return false;
        }

        String newHash = PasswordUtil.hashPassword(newRawPassword);
        boolean updated = userRepository.updatePasswordHash(userId, newHash);
        if (updated) {
            auditRepository.log(userId, "USER_PASSWORD_CHANGE", "USER", userId, "self_service=true");
        }
        return updated;
    }

    public boolean deleteUser(int targetUserId, int actingUserId) {
        User targetUser = userRepository.findById(targetUserId);
        if (targetUser == null) {
            return false;
        }

        if (targetUserId == actingUserId) {
            return false;
        }

        if ("ADMIN".equalsIgnoreCase(targetUser.getRole()) && userRepository.countAdmins() <= 1) {
            return false;
        }

        boolean deleted = userRepository.deleteUser(targetUserId);
        if (deleted) {
            auditRepository.log(actingUserId, "USER_DELETE", "USER", targetUserId, "deleted_by_admin=true");
        }
        return deleted;
    }

    private boolean isValidRole(String role) {
        if (role == null) {
            return false;
        }
        String normalizedRole = role.trim().toUpperCase();
        return "ADMIN".equals(normalizedRole) || "STAFF".equals(normalizedRole);
    }
}
