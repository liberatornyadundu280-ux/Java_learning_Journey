# Quantum Stock - Inventory Management System

A console-based Java inventory manager for basic CRUD-style operations on products.

## Overview

This project is organized as a small OOP learning project with these capabilities:
- Add product
- Remove product by ID
- List all products
- Search products (ID, name, category, or price range)
- Show low-stock report
- Start with prefilled default school/stationery inventory items

## Project Structure

- `Main.java`: Entry point, menu loop, and user interaction routing.
- `Inventory.java`: Core inventory logic and in-memory `List<Item>` storage.
- `Item.java`: Product model, auto-ID generation, and interactive item creation wizard.
- `InputValidator.java`: Reusable integer input validation utility.
- `Interface.java`: Inventory operation contract used by `Inventory`.

## Default Seed Data

On startup, inventory is prefilled with:
- Notebook
- Blue Pen
- Pencil Box
- Eraser
- Geometry Box

All seeded under category `Stationery`.

## How It Works

1. App starts in `Main.main`.
2. `Inventory` is instantiated and immediately prefilled.
3. A menu loop accepts options `1` to `6`.
4. Each option calls the corresponding method in `Inventory`.
5. Data is stored in memory only (no database/file persistence).

## Menu Options

1. `Add Product`: Launches `Item.createItemWizard(...)`.
2. `Remove Product`: Takes ID and removes matching item.
3. `View All Inventory`: Prints all items.
4. `Find Product`: Supports search by:
   - `a` ID
   - `b` Name (contains, case-insensitive)
   - `c` Category (exact, case-insensitive)
   - `d` Price range
5. `Low Stock Alert`: Prints products considered low stock by current rule.
6. `Exit System`: Ends program.

## Build and Run

From the parent folder of `inventory/`:

```powershell
javac inventory/*.java
java inventory.Main
```

If you are already inside the `inventory` folder:

```powershell
javac *.java
```

Then run from the parent directory with:

```powershell
java inventory.Main
```

## Design Notes

- IDs are auto-generated in `Item` starting at `1000` using static counter `counterId`.
- Search uses Java Streams with `Predicate<Item>`.
- `InputValidator` uses a shared static `Scanner` and supports overloads with optional range constraints.

## Current Limitations / Known Issues

- `Low stock` logic appears inverted in `Item.isStock()`:
  - Current check: `reStock < quantity`
  - This flags high quantity as low-stock.
- If user types `quit` inside the add-item wizard, control returns to menu flow, but the partially empty `Item` object is still passed to `addItem(...)`.
- No persistence layer (data resets each run).
- Search number parsing in `findItem(...)` can throw if invalid numeric text is entered.

## Suggested Next Improvements

1. Fix low-stock rule to compare `quantity <= reStock`.
2. Prevent add operation when wizard is cancelled.
3. Add file or DB persistence.
4. Centralize all input through validator for safer numeric parsing.
