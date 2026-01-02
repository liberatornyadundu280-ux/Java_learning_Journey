package movie_system;

import java.util.*;
import java.util.concurrent.*;
import java.time.LocalDateTime;

public class MovieTicketBookingSystem {
    private List<Movie> movies = new CopyOnWriteArrayList<>();
    private Set<Theater> theaters = new CopyOnWriteArraySet<>();
    private Map<Theater, List<Showtime>> theaterShowtimes = new ConcurrentHashMap<>();
    private List<Booking> bookings = new CopyOnWriteArrayList<>();

    // Add a movie to the system
    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    // Add a theater to the system
    public void addTheater(Theater theater) {
        theaters.add(theater);
        theaterShowtimes.put(theater, new ArrayList<>());
    }

    // Add a showtime to a theater
    public void addShowtimeToTheater(Theater theater, Showtime showtime) {
        theaterShowtimes.get(theater).add(showtime);
    }

    // Book a ticket
    public void bookTicket(Movie movie, Showtime showtime, String customerName) {
        if (showtime.isAvailable()) {
            showtime.book(); // Mark the showtime as unavailable
            bookings.add(new Booking(movie, showtime, customerName));
            System.out.println("Booking successful for " + customerName);
        } else {
            System.out.println("Sorry, this showtime is unavailable.");
        }
    }

    // Search for movies by name
    public List<Movie> searchMoviesByName(String name) {
        List<Movie> result = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getName().contains(name)) {
                result.add(movie);
            }
        }
        return result;
    }

    // Sort movies by name
    public void sortMovies() {
        Collections.sort(movies);
    }

    // Search for showtimes by movie and theater
    public List<Showtime> searchShowtimes(Movie movie, Theater theater) {
        List<Showtime> result = new ArrayList<>();
        for (Showtime showtime : theaterShowtimes.get(theater)) {
            if (showtime.getMovie().equals(movie)) {
                result.add(showtime);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        MovieTicketBookingSystem system = new MovieTicketBookingSystem();

        // Create movies
        Movie movie1 = new Movie("Inception", "Sci-Fi", 148);
        Movie movie2 = new Movie("The Dark Knight", "Action", 152);
        system.addMovie(movie1);
        system.addMovie(movie2);

        // Create theaters
        Theater theater1 = new Theater("IMAX Theater", "City Center");
        Theater theater2 = new Theater("Regal Cinemas", "Downtown");
        system.addTheater(theater1);
        system.addTheater(theater2);

        // Create showtimes
        Showtime showtime1 = new Showtime(LocalDateTime.of(2025, 6, 10, 19, 30), movie1);
        Showtime showtime2 = new Showtime(LocalDateTime.of(2025, 6, 10, 21, 30), movie2);
        system.addShowtimeToTheater(theater1, showtime1);
        system.addShowtimeToTheater(theater2, showtime2);

        // Booking a ticket
        system.bookTicket(movie1, showtime1, "Geek");

        // Searching and sorting movies
        List<Movie> searchResults = system.searchMoviesByName("Inception");
        System.out.println("Search Results: " + searchResults);

        system.sortMovies();
        System.out.println("Sorted Movies: " + system.movies);
    }
}