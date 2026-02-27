# Demo Script

Use this script during capstone evaluation.

## Pre-demo

1. Build and run:
```powershell
mvn -q -DskipTests compile
mvn -q exec:java
```
2. Login as default admin:
- `admin / admin123`

## Demo Flow (10-12 min)

1. Role-based UI
- Show admin menu includes user/stock/report actions.

2. Create users
- Create one `STAFF` and one `ADMIN` user.
- Show users list.

3. Inventory operations
- Add a product (admin).
- Adjust stock quantity (admin).
- Record sale (admin).

4. Staff workflow
- Log out and login as staff user.
- Show staff-specific menu (no admin-only options).
- Record sale and view inventory.

5. Security workflow
- Staff changes own password.
- Login again with new password.

6. Admin management
- Login as admin.
- Manage user: edit role/username, reset password, delete user.
- Show guardrails (cannot delete self / last admin).

7. Reporting
- Open Inventory Transactions report (`all`, `today`, `by user id`).
- Open Audit Logs report (`all`, `today`, `by user id`).

## Evaluation Highlights

- Real SQL persistence
- RBAC correctness
- Data integrity (no negative stock sales)
- Security (hashed passwords, reset instead of reveal)
- Traceability (audit + transactions)
