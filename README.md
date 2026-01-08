# Java Learning Journey

This repository is a personal learning workspace where you progress through Java fundamentals to intermediate topics by weekly exercises and small projects.

**Roadmap (suggested order)**

- **Variables & Data Types:** primitives, reference types, literals, type casting, wrapper classes.
- **Control Flow:** `if`/`else`, `switch`, loops (`for`, `while`, `do-while`), `break`/`continue`.
- **Methods & Parameters:** method signatures, return types, overloading, recursion.
- **OOP Fundamentals:** classes, objects, constructors, encapsulation, access modifiers.
- **Inheritance & Polymorphism:** `extends`, `super`, method overriding, `instanceof`.
- **Interfaces & Abstract Classes:** design via contracts, default/static methods, multiple inheritance via interfaces.
- **Collections & Generics:** `List`, `Set`, `Map`, iterators, generics, `Comparable`/`Comparator`.
- **Exceptions & Error Handling:** checked vs unchecked exceptions, `try`/`catch`/`finally`, custom exceptions.
- **I/O & File Handling:** `java.io` and `java.nio`, reading/writing files, streams.
- **Java 8+ Features:** lambdas, streams, `Optional`, new Date/Time API.
- **Concurrency:** threads, `Runnable`, `Callable`, `ExecutorService`, synchronization, concurrent collections.
- **Testing & Tooling:** unit testing with JUnit, build with Maven/Gradle, linting, and formatting.
- **Networking & APIs:** sockets, HTTP clients, JSON parsing.
- **Projects & Patterns:** small apps (e.g., Online Movie Ticket System), design patterns, refactoring.

**Repository layout (typical)**

- `week1/`, `week2/`, ... — weekly exercises and small examples (each week contains `.java` files for practice).
- `week8/ReaderClass.java` — example utility or practice class in Week 8.
- `weekly_project/` — larger project(s) developed over multiple sessions.
  - `weekly_project/Online_Movie_Ticket_System/movie_system/` — small demo movie booking system with `Main.java`, `Movie.java`, `Theater.java`, `Showtime.java`, `Booking.java`, `InputValidator.java`, and a README describing that project.
- Compiled artifacts (`*.class`) sometimes appear; prefer adding a `.gitignore` to exclude them.
- `package.json` / `node_modules/` may exist if editor tooling or scripts are used; not required for Java.

**How to run examples (basic)**

From a folder containing Java sources:

```cmd
javac *.java
java Main
```

If a package is used, compile/run from the repository root and include the package path.

**Study tips & workflow**

- Work one topic at a time; commit small, focused exercises.
- Convert exercises into tiny projects to apply multiple concepts (OOP + collections + I/O).
- Add unit tests (JUnit) as you go — it helps verify understanding.
- Keep a short weekly note (single file) summarizing what you learned and any questions.

**Next steps I can help with**

- Create a `.gitignore` to exclude `*.class`, `node_modules/`, and IDE files.
- Generate a contents index linking each `weekN` folder and important files.
- Expand the `Online_Movie_Ticket_System` README with runnable examples and sample outputs.
