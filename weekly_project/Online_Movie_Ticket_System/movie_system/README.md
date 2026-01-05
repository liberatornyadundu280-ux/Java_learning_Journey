# Online Movie Ticket System (movie_system)

Small Java demo for a movie ticket booking system (package `movie_system`).

## Build & Run

From the project folder containing the `movie_system` directory run:

```cmd
javac movie_system\*.java
java movie_system.Main
```

## Usage

- The provided `Main` class runs a short demo: creates movies, theaters, showtimes, makes a booking, then prints search and sorted movie lists.

Example output:

```
Booking successful for Geek
Search Results: [Movie{name='Inception', genre='Sci-Fi', duration=148}]
Sorted Movies: [Movie{name='Inception', genre='Sci-Fi', duration=148}, Movie{name='The Dark Knight', genre='Action', duration=152}]
```

## Interactive Input

- `InputValidator` contains helper methods to prompt for integers from stdin. To exercise it, modify `Main` to call `InputValidator.getValidInt(...)`.

## Notes

- Ensure filenames match public class names exactly (case-sensitive on some filesystems). The `movie_system` package contains `Movie.java` and `Showtime.java` matching their classes.
