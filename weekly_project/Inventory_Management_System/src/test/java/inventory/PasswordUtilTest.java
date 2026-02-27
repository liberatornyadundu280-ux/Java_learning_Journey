package inventory;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PasswordUtilTest {

    @Test
    void hashAndVerifyShouldWork() {
        String raw = "StrongPass123";
        String hash = PasswordUtil.hashPassword(raw);

        assertNotEquals(raw, hash);
        assertTrue(PasswordUtil.verifyPassword(raw, hash));
        assertFalse(PasswordUtil.verifyPassword("wrong-pass", hash));
    }
}
