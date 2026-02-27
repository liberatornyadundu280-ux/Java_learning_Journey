package inventory;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AuthServiceIntegrationTest {
    private Path tempDbPath;

    @BeforeEach
    void setup() throws Exception {
        tempDbPath = Files.createTempFile("ims-test-", ".db");
        System.setProperty("IMS_DB_URL", "jdbc:sqlite:" + tempDbPath.toString().replace("\\", "/"));
        DatabaseInitializer.resetForTests();
        new Inventory();
    }

    @AfterEach
    void tearDown() throws Exception {
        System.clearProperty("IMS_DB_URL");
        DatabaseInitializer.resetForTests();
        if (tempDbPath != null) {
            Files.deleteIfExists(tempDbPath);
        }
    }

    @Test
    void defaultAdminShouldAuthenticate() {
        AuthService authService = new AuthService();
        User admin = authService.authenticate("admin", "admin123");
        assertNotNull(admin);
    }

    @Test
    void createUserAndChangeOwnPasswordShouldWork() {
        AuthService authService = new AuthService();
        User admin = authService.authenticate("admin", "admin123");
        assertNotNull(admin);

        boolean created = authService.createUser(admin.getId(), "staff1", "staff123", "STAFF");
        assertTrue(created);

        User staff = authService.authenticate("staff1", "staff123");
        assertNotNull(staff);

        boolean changed = authService.changeOwnPassword(staff.getId(), "staff1", "staff123", "staff456");
        assertTrue(changed);

        User oldLogin = authService.authenticate("staff1", "staff123");
        assertNull(oldLogin);

        User newLogin = authService.authenticate("staff1", "staff456");
        assertNotNull(newLogin);
    }
}
