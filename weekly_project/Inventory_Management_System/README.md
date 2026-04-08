# Inventory Management System (Capstone)

Console-based Inventory Management System built with Java, SQLite, JDBC, and role-based access.

## Features

- Authentication with hashed passwords (PBKDF2)
- Role-based access (`ADMIN`, `STAFF`)
- Inventory CRUD and search
- Live stock operations:
  - Admin stock adjustment
  - Sales recording (stock decrement)
- User management (admin):
  - Create users (`STAFF`/`ADMIN`)
  - View/edit/delete users
  - Password reset
- Self-service password change
- Reporting (admin):
  - Inventory transaction report (`all`, `today`, `by user`)
  - Audit log report (`all`, `today`, `by user`)
- SQL persistence with startup bootstrap
- SQLite database can be inspected directly outside the application

## Tech Stack

- Java 17+
- SQLite (JDBC: `org.xerial:sqlite-jdbc`)
- Maven
- JUnit 5 (test foundation)

## Project Structure

```text
src/main/java/inventory/
  Main.java
  Inventory.java
  InventoryOperations.java
  Item.java
  ItemRepository.java
  User.java
  UserRepository.java
  AuthService.java
  PasswordUtil.java
  Database.java
  DatabaseInitializer.java
  AuditRepository.java
  InventoryTransactionEntry.java
  AuditLogEntry.java

src/main/resources/db/
  schema.sql
  seed.sql
  migrations/
    V1__schema.sql
    V2__seed.sql

src/test/java/inventory/
  PasswordUtilTest.java
  AuthServiceIntegrationTest.java
```

## Setup (Windows PowerShell)

1. Install JDK 17+
```powershell
java -version
javac -version
```

2. Install Maven 3.9+
```powershell
mvn -v
```

3. Build
```powershell
mvn -q -DskipTests compile
```

4. Run
```powershell
mvn -q exec:java
```

## Database Access Outside The App

This project uses a local SQLite database file:

- `data/inventory.db`

You can inspect the database directly without launching the Java application.

### Option 1: VS Code Terminal With `sqlite3`

If `sqlite3` is installed and available in your `PATH`, open the project folder in a terminal and run:

```powershell
sqlite3 "data/inventory.db"
```

Then inside SQLite:

```sql
.headers on
.mode column
.tables
SELECT * FROM users;
SELECT * FROM items;
SELECT * FROM audit_logs;
SELECT * FROM inventory_transactions;
```

Exit SQLite with:

```sql
.exit
```

### Option 2: DB Browser / SQLite Extension

You can also open `data/inventory.db` with:

- DB Browser for SQLite
- a VS Code SQLite extension

This is useful for browsing tables and running ad hoc SQL queries visually.

## Default Login

- Username: `admin`
- Password: `admin123`

## DB Configuration

Default DB URL:
- `jdbc:sqlite:data/inventory.db`

Override with env var or JVM property:
```powershell
$env:IMS_DB_URL = "jdbc:sqlite:data/my_capstone.db"
mvn -q exec:java
```

## Tests

```powershell
mvn test
```

## Persistence Improvements

Recent updates made to the persistence layer:

- SQLite foreign key enforcement is enabled for each JDBC connection
- Default admin bootstrap now repairs invalid or legacy password values
- Broken user seed data was removed to avoid plain text password issues
- "Today" report queries now use local time instead of UTC-only date checks
- Maven compiler settings now match the documented Java 17 requirement

## Notes

- Passwords are not retrievable in plaintext (one-way hashes only).
- `STAFF` UI is intentionally limited to operational actions.
- The app uses SQLite, so the database is stored as a local `.db` file rather than a separate database server.
