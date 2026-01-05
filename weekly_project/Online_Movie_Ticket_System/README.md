# Online Movie Ticket System

Simple Java demo for a movie ticket booking system.

## Overview

- Small console app demonstrating movies, theaters, showtimes and bookings.
- Package: `movie_system`.

## Files

- `Main.java` — program entrypoint and demo usage.
- `Movie.java`, `Showtime.java`, `Theater.java`, `Booking.java`, `InputValidator.java` — core classes.

## Requirements

- Java 11 or later.

## Build & Run (Windows)

Open a Command Prompt in the project root and run:

```cmd
javac movie_system\*.java
java movie_system.Main
```

## Notes

- Filename/case must match public class names on case-sensitive filesystems. I fixed earlier mismatches (`movie.java` -> `Movie.java`, `showTime.java` -> `Showtime.java`).
- The demo creates two movies, two theaters, two showtimes, makes one booking and prints search/sort results.

If you want, I can add a short README section with usage examples or a Makefile/gradle wrapper.
