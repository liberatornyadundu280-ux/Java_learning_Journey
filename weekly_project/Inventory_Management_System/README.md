# Inventory Management System

This is a Java console-based inventory MVP using package `inventory`.

## Project layout

```text
Inventory_Management_System/
  src/
    main/
      java/
        inventory/
          Main.java            # entry point
          Inventory.java       # core inventory operations
          Item.java            # product model
          InputValidator.java  # input validation helpers
          InventoryOperations.java # inventory contract
      resources/               # reserved for config/sql/resources
    test/
      java/                    # reserved for unit tests
  docs/
    PROJECT_OVERVIEW.md
  README.md
```

## How to run

From project root:

```powershell
New-Item -ItemType Directory -Force out | Out-Null
javac -d out src/main/java/inventory/*.java
java -cp out inventory.Main
```
