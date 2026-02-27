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

## Notes

- Passwords are not retrievable in plaintext (one-way hashes only).
- `STAFF` UI is intentionally limited to operational actions.
