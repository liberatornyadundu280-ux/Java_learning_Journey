# Inventory Management System

Java console inventory app with a SQL backend (SQLite via JDBC).

## Current Stage

- Stage 1 complete: core console bugs fixed and codebase cleaned.
- Stage 2 expanded: SQL persistence, authentication, role-based UX, stock/sales operations, plus audit and transaction tracking.

## Project Layout

```text
Inventory_Management_System/
  src/
    main/
      java/inventory/
        Main.java
        Inventory.java
        InventoryOperations.java
        Item.java
        InputValidator.java
        AuthService.java
        UserRepository.java
        User.java
        PasswordUtil.java
        Database.java
        DatabaseInitializer.java
        ItemRepository.java
      resources/db/
        schema.sql
        seed.sql
    test/java/
  pom.xml
  README.md
  docs/PROJECT_OVERVIEW.md
```

## Environment Setup (Windows + PowerShell)

1. Install JDK 17+
```powershell
java -version
javac -version
```

2. Install Maven 3.9+
```powershell
mvn -v
```

3. From project root, compile with dependencies
```powershell
mvn -q -DskipTests compile
```

4. Run the app
```powershell
mvn -q exec:java
```

## Database Setup Behavior

- Default DB URL: `jdbc:sqlite:data/inventory.db`
- On first run:
  - `schema.sql` creates tables.
  - `seed.sql` inserts default inventory records.
  - Startup logic creates default admin user if missing.

## Authentication Setup

- Login is required before the main menu is shown.
- Passwords are stored as salted PBKDF2 hashes (`PBKDF2WithHmacSHA256`), not plain text.
- Default credentials (first run):
  - Username: `admin`
  - Password: `admin123`
- Roles:
  - `ADMIN`: full access, including user management (create users (staff/admin), view users, edit user details, force reset passwords, delete users)
  - `STAFF`: operational access (view/search/low-stock, record sale), cannot add/remove products or directly edit stock
- Security rule:
  - Admin cannot view plaintext passwords. Passwords are one-way hashed and only resettable.
- Self-service:
  - Any logged-in user can change their own password from main menu option `9`.

## Optional: Custom DB Location

Set env var before run:

```powershell
$env:IMS_DB_URL = "jdbc:sqlite:data/my_capstone.db"
mvn -q exec:java
```

## Why `java -cp out inventory.Main` now fails

Stage 2 uses JDBC driver dependency from Maven (`sqlite-jdbc`).
Running with plain `java -cp out` does not include external dependency jars, so DB init fails.

## Next Stage\n\n- Add test suite (unit + integration)\n- Add migration/versioning scripts\n- Optional: export reports to CSV

- Add user management commands (create admin)
- Repository tests + migration/versioning scripts
