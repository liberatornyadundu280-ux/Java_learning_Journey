# Quantum Stock - Inventory Management System

Console-based Java inventory manager evolving into a capstone-ready project.

## Stage Status

- Stage 1: Completed (bug fixes, cleanup, architecture tidying).
- Stage 2: In progress (SQL backend + authentication + user management + stock operations + audit/transaction reports).

## Stage 2 Additions

- JDBC connection management (`Database.java`)
- Startup schema/seed bootstrap (`DatabaseInitializer.java`)
- SQL repositories:
  - `ItemRepository.java`
  - `UserRepository.java`
  - `AuditRepository.java`
- Authentication service (`AuthService.java`)
- Secure password hashing utility (`PasswordUtil.java`, PBKDF2)
- SQL scripts under `src/main/resources/db/`
  - `schema.sql`
  - `seed.sql`

## Tables

- `items`
  - `id`, `name`, `price`, `quantity`, `category`, `restock_level`
- `users`
  - `id`, `username`, `password_hash`, `role`, `created_at`
- `inventory_transactions`
  - stock movement ledger (`ADJUSTMENT`, `SALE`) with before/after quantities and actor user
- `audit_logs`
  - security/operations event trail (login, user management, stock edits, sales)

## Runtime Notes

- Default database: `jdbc:sqlite:data/inventory.db`
- Uses Maven dependency `org.xerial:sqlite-jdbc`
- App requires login before menu.
- Default bootstrapped admin account:
  - Username: `admin`
  - Password: `admin123`
- Roles:
  - `ADMIN`: full system + user/stock management + report access
  - `STAFF`: operational flows (view/search/report low stock, record sales, self password change)
- Admin report screens:
  - Inventory transactions (`all`, `today`, `by user id`)
  - Audit logs (`all`, `today`, `by user id`)
- Security:
  - Passwords are not viewable in plaintext.
  - Passwords are stored hashed only.

## Build and Run

```powershell
mvn -q -DskipTests compile
mvn -q exec:java
```

## Planned Next Work

1. Add unit/integration tests for repository/auth methods.
2. Add migration/versioning scripts.
3. Optional: CSV export for reports.
