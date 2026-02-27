package inventory;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final int MAX_LOGIN_ATTEMPTS = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inventory myInventory;
        try {
            myInventory = new Inventory();
        } catch (IllegalStateException e) {
            System.out.println("Startup error: could not initialize database.");
            System.out.println("Details: " + e.getMessage());
            scanner.close();
            return;
        }

        AuthService authService = new AuthService();
        User currentUser = login(scanner, authService);
        if (currentUser == null) {
            System.out.println("Too many failed login attempts. Exiting.");
            scanner.close();
            return;
        }

        boolean exit = false;

        System.out.println("\n\n\n==========================================");
        System.out.println("   QUANTUM STOCK - INVENTORY SYSTEM v1.0  ");
        System.out.println("==========================================");
        System.out.println("Logged in as: " + currentUser.getUsername() + " (" + currentUser.getRole() + ")");

        while (!exit) {
            if (isAdmin(currentUser)) {
                exit = handleAdminMenu(scanner, authService, myInventory, currentUser);
            } else {
                exit = handleStaffMenu(scanner, authService, myInventory, currentUser);
            }
        }
        scanner.close();
    }

    private static User login(Scanner scanner, AuthService authService) {
        System.out.println("\n--- LOGIN REQUIRED ---");
        for (int attempts = 1; attempts <= MAX_LOGIN_ATTEMPTS; attempts++) {
            System.out.print("Username: ");
            String username = scanner.nextLine().trim();
            System.out.print("Password: ");
            String password = scanner.nextLine();

            User user = authService.authenticate(username, password);
            if (user != null) {
                System.out.println("Login successful.");
                return user;
            }

            int remaining = MAX_LOGIN_ATTEMPTS - attempts;
            if (remaining > 0) {
                System.out.println("Invalid credentials. Attempts left: " + remaining);
            }
        }
        return null;
    }

    private static boolean isAdmin(User user) {
        return "ADMIN".equalsIgnoreCase(user.getRole());
    }

    private static void createUserFlow(Scanner scanner, AuthService authService, User currentUser) {
        System.out.println("\n--- CREATE USER ---");
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        if (username.isEmpty()) {
            System.out.println("Username cannot be empty.");
            return;
        }

        System.out.print("Password (min 6 chars): ");
        String password = scanner.nextLine();
        if (password.length() < 6) {
            System.out.println("Password must be at least 6 characters.");
            return;
        }

        System.out.print("Confirm Password: ");
        String confirmPassword = scanner.nextLine();
        if (!password.equals(confirmPassword)) {
            System.out.println("Passwords do not match.");
            return;
        }

        System.out.print("Role (STAFF/ADMIN, default STAFF): ");
        String roleInput = scanner.nextLine().trim();
        String role = roleInput.isEmpty() ? "STAFF" : roleInput.toUpperCase();
        if (!"STAFF".equals(role) && !"ADMIN".equals(role)) {
            System.out.println("Invalid role. Please choose STAFF or ADMIN.");
            return;
        }

        boolean created = authService.createUser(currentUser.getId(), username, password, role);
        if (created) {
            System.out.println(role + " user created successfully.");
        } else {
            System.out.println("Could not create user. Username may already exist or input is invalid.");
        }
    }

    private static void viewAllUsersFlow(AuthService authService) {
        List<User> users = authService.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }

        System.out.println("\n--- ALL SYSTEM USERS ---");
        System.out.printf("%-5s %-20s %-10s %-20s%n", "ID", "Username", "Role", "Created At");
        for (User user : users) {
            String createdAt = user.getCreatedAt() == null ? "-" : user.getCreatedAt();
            System.out.printf("%-5d %-20s %-10s %-20s%n",
                    user.getId(), user.getUsername(), user.getRole(), createdAt);
        }
    }

    private static void manageUserFlow(Scanner scanner, AuthService authService, User currentUser) {
        System.out.println("\n--- MANAGE USER ---");
        System.out.println("Note: Passwords are securely hashed and cannot be viewed.");
        viewAllUsersFlow(authService);
        int userId = InputValidator.getValidInt(scanner, "Enter user ID to manage: ", 1);

        User target = authService.getUserById(userId);
        if (target == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.println("Selected: " + target.getUsername() + " (" + target.getRole() + ")");
        printTwoColumnMenu(
                "a. Edit username/role",
                "b. Reset password",
                "c. Delete user");
        System.out.print("Choose action: ");
        String action = scanner.nextLine().trim().toLowerCase();

        switch (action) {
            case "a":
                editUserDetailsFlow(scanner, authService, currentUser, target);
                break;
            case "b":
                resetUserPasswordFlow(scanner, authService, currentUser, target);
                break;
            case "c":
                deleteUserFlow(scanner, authService, currentUser, target);
                break;
            default:
                System.out.println("Invalid action.");
        }
    }

    private static void editUserDetailsFlow(Scanner scanner, AuthService authService, User currentUser, User target) {
        System.out.print("New username (leave blank to keep '" + target.getUsername() + "'): ");
        String newUsername = scanner.nextLine().trim();
        if (newUsername.isEmpty()) {
            newUsername = target.getUsername();
        }

        System.out.print("New role ADMIN/STAFF (leave blank to keep '" + target.getRole() + "'): ");
        String newRole = scanner.nextLine().trim();
        if (newRole.isEmpty()) {
            newRole = target.getRole();
        }

        if (currentUser.getId() == target.getId() && !"ADMIN".equalsIgnoreCase(newRole)) {
            System.out.println("You cannot downgrade your own account from ADMIN.");
            return;
        }

        boolean updated = authService.updateUserDetails(currentUser.getId(), target.getId(), newUsername, newRole);
        if (updated) {
            System.out.println("User details updated successfully.");
        } else {
            System.out.println("Could not update user. Username may already exist or role is invalid.");
        }
    }

    private static void resetUserPasswordFlow(Scanner scanner, AuthService authService, User currentUser, User target) {
        if (currentUser.getId() == target.getId()) {
            System.out.println("Use 'Change My Password' option from the main menu for your own account.");
            return;
        }

        System.out.print("New password (min 6 chars): ");
        String newPassword = scanner.nextLine();
        if (newPassword.length() < 6) {
            System.out.println("Password must be at least 6 characters.");
            return;
        }

        System.out.print("Confirm new password: ");
        String confirmPassword = scanner.nextLine();
        if (!newPassword.equals(confirmPassword)) {
            System.out.println("Passwords do not match.");
            return;
        }

        boolean reset = authService.resetUserPassword(currentUser.getId(), target.getId(), newPassword);
        if (reset) {
            System.out.println("Password reset successfully.");
        } else {
            System.out.println("Could not reset password.");
        }
    }

    private static void changeOwnPasswordFlow(Scanner scanner, AuthService authService, User currentUser) {
        System.out.println("\n--- CHANGE MY PASSWORD ---");
        System.out.print("Current password: ");
        String currentPassword = scanner.nextLine();

        System.out.print("New password (min 6 chars): ");
        String newPassword = scanner.nextLine();
        if (newPassword.length() < 6) {
            System.out.println("Password must be at least 6 characters.");
            return;
        }

        System.out.print("Confirm new password: ");
        String confirmPassword = scanner.nextLine();
        if (!newPassword.equals(confirmPassword)) {
            System.out.println("Passwords do not match.");
            return;
        }
        if (newPassword.equals(currentPassword)) {
            System.out.println("New password must be different from current password.");
            return;
        }

        boolean changed = authService.changeOwnPassword(
                currentUser.getId(),
                currentUser.getUsername(),
                currentPassword,
                newPassword);
        if (changed) {
            System.out.println("Password changed successfully.");
        } else {
            System.out.println("Could not change password. Current password may be incorrect.");
        }
    }

    private static void deleteUserFlow(Scanner scanner, AuthService authService, User currentUser, User target) {
        if (currentUser.getId() == target.getId()) {
            System.out.println("You cannot delete your own account.");
            return;
        }

        System.out.print("Type DELETE to confirm deletion of user '" + target.getUsername() + "': ");
        String confirm = scanner.nextLine().trim();
        if (!"DELETE".equals(confirm)) {
            System.out.println("Deletion cancelled.");
            return;
        }

        boolean deleted = authService.deleteUser(target.getId(), currentUser.getId());
        if (deleted) {
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("Could not delete user. This may be the last ADMIN account.");
        }
    }

    private static void editStockFlow(Scanner scanner, Inventory inventory, User currentUser) {
        System.out.println("\n--- EDIT STOCK QUANTITY ---");
        int itemId = InputValidator.getValidInt(scanner, "Enter item ID: ", 1);
        int newQuantity = InputValidator.getValidInt(scanner, "Enter new quantity (>= 0): ", 0);
        inventory.editStockQuantity(itemId, newQuantity, currentUser.getId());
    }

    private static void recordSaleFlow(Scanner scanner, Inventory inventory, User currentUser) {
        System.out.println("\n--- RECORD SALE ---");
        int itemId = InputValidator.getValidInt(scanner, "Enter item ID sold: ", 1);
        int soldQuantity = InputValidator.getValidInt(scanner, "Enter quantity sold (>= 1): ", 1);
        inventory.recordSale(itemId, soldQuantity, currentUser.getId());
    }

    private static void printTwoColumnMenu(String... options) {
        final int leftColumnWidth = 36;
        for (int i = 0; i < options.length; i += 2) {
            String left = options[i];
            String right = (i + 1 < options.length) ? options[i + 1] : "";
            System.out.printf("%-" + leftColumnWidth + "s %s%n", left, right);
        }
    }

    private static boolean handleAdminMenu(Scanner scanner, AuthService authService, Inventory inventory, User currentUser) {
        System.out.println("\n--- MAIN MENU (ADMIN) ---");
        printTwoColumnMenu(
                "1. [NEW] Add Product",
                "2. [DEL] Remove Product",
                "3. [LST] View All Inventory",
                "4. [SRCH] Find Product by ID",
                "5. [RPT] Low Stock Alert",
                "6. [USR] Create User",
                "7. [USR] View All Users",
                "8. [USR] Manage User",
                "9. [SEC] Change My Password",
                "10. [STK] Edit Stock Quantity",
                "11. [SALE] Record Sale",
                "12. [RPT] Inventory Transactions",
                "13. [RPT] Audit Logs",
                "14. Exit System");
        System.out.print("\nSelect Action: ");

        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                Item newItem = new Item();
                boolean isCreated = newItem.createItemWizard(scanner);
                if (isCreated) {
                    inventory.addItem(newItem);
                } else {
                    System.out.println("Entry cancelled. Nothing was saved.");
                }
                return false;
            case "2":
                int id = InputValidator.getValidInt(scanner, "Enter item id to remove: ");
                inventory.removeItem(id);
                return false;
            case "3":
                inventory.viewItems();
                return false;
            case "4":
                inventory.findItem(scanner);
                return false;
            case "5":
                inventory.checkLowStock();
                return false;
            case "6":
                createUserFlow(scanner, authService, currentUser);
                return false;
            case "7":
                viewAllUsersFlow(authService);
                return false;
            case "8":
                manageUserFlow(scanner, authService, currentUser);
                return false;
            case "9":
                changeOwnPasswordFlow(scanner, authService, currentUser);
                return false;
            case "10":
                editStockFlow(scanner, inventory, currentUser);
                return false;
            case "11":
                recordSaleFlow(scanner, inventory, currentUser);
                return false;
            case "12":
                viewInventoryTransactionsReportFlow(scanner);
                return false;
            case "13":
                viewAuditLogsReportFlow(scanner);
                return false;
            case "14":
                System.out.println("Closing system... Goodbye!");
                return true;
            default:
                System.out.println(">> Invalid selection. Please choose 1-14.");
                return false;
        }
    }

    private static boolean handleStaffMenu(Scanner scanner, AuthService authService, Inventory inventory, User currentUser) {
        System.out.println("\n--- MAIN MENU (STAFF) ---");
        printTwoColumnMenu(
                "1. [LST] View All Inventory",
                "2. [SRCH] Find Product by ID",
                "3. [RPT] Low Stock Alert",
                "4. [SALE] Record Sale",
                "5. [SEC] Change My Password",
                "6. Exit System");
        System.out.print("\nSelect Action: ");

        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                inventory.viewItems();
                return false;
            case "2":
                inventory.findItem(scanner);
                return false;
            case "3":
                inventory.checkLowStock();
                return false;
            case "4":
                recordSaleFlow(scanner, inventory, currentUser);
                return false;
            case "5":
                changeOwnPasswordFlow(scanner, authService, currentUser);
                return false;
            case "6":
                System.out.println("Closing system... Goodbye!");
                return true;
            default:
                System.out.println(">> Invalid selection. Please choose 1-6.");
                return false;
        }
    }

    private static void viewInventoryTransactionsReportFlow(Scanner scanner) {
        ItemRepository itemRepository = new ItemRepository();
        System.out.println("\n--- INVENTORY TRANSACTIONS REPORT ---");
        System.out.println("a. All");
        System.out.println("b. Today");
        System.out.println("c. By User ID");
        System.out.print("Choose filter: ");
        String filter = scanner.nextLine().trim().toLowerCase();

        List<InventoryTransactionEntry> entries;
        switch (filter) {
            case "a":
                entries = itemRepository.getAllInventoryTransactions();
                break;
            case "b":
                entries = itemRepository.getTodayInventoryTransactions();
                break;
            case "c":
                int userId = InputValidator.getValidInt(scanner, "Enter actor user ID: ", 1);
                entries = itemRepository.getInventoryTransactionsByActorUserId(userId);
                break;
            default:
                System.out.println("Invalid filter.");
                return;
        }

        if (entries.isEmpty()) {
            System.out.println("No inventory transactions found.");
            return;
        }

        System.out.printf("%-5s %-6s %-12s %-8s %-8s %-8s %-8s %-19s%n",
                "ID", "Item", "Type", "Change", "Before", "After", "Actor", "Created At");
        for (InventoryTransactionEntry entry : entries) {
            String actor = entry.getActorUserId() == null ? "-" : String.valueOf(entry.getActorUserId());
            System.out.printf("%-5d %-6d %-12s %-8d %-8d %-8d %-8s %-19s%n",
                    entry.getId(),
                    entry.getItemId(),
                    entry.getTransactionType(),
                    entry.getQuantityChange(),
                    entry.getQuantityBefore(),
                    entry.getQuantityAfter(),
                    actor,
                    entry.getCreatedAt());
        }
    }

    private static void viewAuditLogsReportFlow(Scanner scanner) {
        AuditRepository auditRepository = new AuditRepository();
        System.out.println("\n--- AUDIT LOGS REPORT ---");
        System.out.println("a. All");
        System.out.println("b. Today");
        System.out.println("c. By User ID");
        System.out.print("Choose filter: ");
        String filter = scanner.nextLine().trim().toLowerCase();

        List<AuditLogEntry> logs;
        switch (filter) {
            case "a":
                logs = auditRepository.getAllLogs();
                break;
            case "b":
                logs = auditRepository.getTodayLogs();
                break;
            case "c":
                int userId = InputValidator.getValidInt(scanner, "Enter actor user ID: ", 1);
                logs = auditRepository.getLogsByActorUserId(userId);
                break;
            default:
                System.out.println("Invalid filter.");
                return;
        }

        if (logs.isEmpty()) {
            System.out.println("No audit logs found.");
            return;
        }

        System.out.printf("%-5s %-8s %-20s %-10s %-8s %-19s%n",
                "ID", "Actor", "Action", "Target", "TgtID", "Created At");
        for (AuditLogEntry log : logs) {
            String actor = log.getActorUserId() == null ? "-" : String.valueOf(log.getActorUserId());
            String targetType = log.getTargetType() == null ? "-" : log.getTargetType();
            String targetId = log.getTargetId() == null ? "-" : String.valueOf(log.getTargetId());
            System.out.printf("%-5d %-8s %-20s %-10s %-8s %-19s%n",
                    log.getId(),
                    actor,
                    log.getAction(),
                    targetType,
                    targetId,
                    log.getCreatedAt());
            if (log.getDetails() != null && !log.getDetails().isEmpty()) {
                System.out.println("      details: " + log.getDetails());
            }
        }
    }
}
