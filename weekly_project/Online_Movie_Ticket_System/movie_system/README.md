# Online Movie Ticket System (movie_system)

Small Java demo for a movie ticket booking system (package `movie_system`).

## Build & Run

From the project folder containing the `movie_system` directory run:

```cmd
javac movie_system\*.java
java movie_system.Main
```

# Online Movie Ticket System (movie_system)

This small Java demo implements a minimal movie ticket booking system used for learning Java concepts (classes, I/O, validation, and simple domain modeling).

## Project contents

- **`Booking.java`**: Represents a booking/reservation made by a user (holds customer name, showtime, seats, etc.).
- **`InputValidator.java`**: Console input helpers that validate and parse user input (e.g., integers, selections).
- **`Main.java`**: Demo runner and entry point. Creates sample `Movie`, `Theater`, and `Showtime` objects and performs example bookings and searches.
- **`Movie.java`**: Domain model for a movie (title, genre, duration, and helper methods like `toString`).
- **`Showtime.java`**: Represents a scheduled screening of a `Movie` at a `Theater` (time, available seats, pricing, etc.).
- **`Theater.java`**: Domain model for a theater or screen (name, capacity, address-like metadata).
- **`package.json` / `package-lock.json` / `node_modules/`**: Present if Node tooling was used (e.g., for auxiliary scripts or editor integrations). Not required to build the Java code.
- **Compiled `.class` files**: (e.g., `*.class`) appear when Java files are compiled; they can be removed or ignored in source control.

## Build & Run (Java)

From this project folder run:

```cmd
javac *.java
java Main
```

Notes:

- If classes are in a package, compile/run from the project root and include package path accordingly.
- Delete `*.class` files before sharing if you want only source files tracked.

## How the pieces fit

- `Main` wires together `Movie`, `Theater`, and `Showtime` objects and demonstrates creating a `Booking`.
- `InputValidator` is a reusable helper — swap `Main`'s demo for an interactive menu to exercise it.

## Recommended next steps

- Add a `README` at the `Java_learning_Journey` root summarizing subprojects (if you want a top-level overview).
- Consider adding a `.gitignore` to exclude `*.class`, `node_modules/`, and IDE files.

## Contact / Notes

This README is intended for quick orientation while learning. If you want, I can:

- Expand this README with file links and example outputs.
- Create a top-level README in the `Java_learning_Journey` folder that enumerates all subfolders and their purpose.
