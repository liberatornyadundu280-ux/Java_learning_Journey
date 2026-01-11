package movie_system.service;

import movie_system.model.*;
import movie_system.persistence.*;
import movie_system.utils.InputValidator;

import java.time.LocalDateTime;
import java.util.List;

public class AdminService {

    public static void handleAddHall(
            List<Theater> theaters,
            TheaterRepository theaterRepo,
            AdminService adminService) {
        System.out.println("\n--- ADD HALL ---");

        if (theaters.isEmpty()) {
            System.out.println("No theaters available.");
            return;
        }

        for (Theater t : theaters) {
            System.out.println(t.getTheaterId() + " - " + t.getName());
        }

        int theaterId = InputValidator.getValidInt(
                "Select Theater ID: ", 1, Integer.MAX_VALUE);

        Theater theater = theaters.stream()
                .filter(t -> t.getTheaterId() == theaterId)
                .findFirst()
                .orElse(null);

        if (theater == null) {
            System.out.println("Theater not found.");
            return;
        }

        int hallNumber = InputValidator.getValidInt(
                "Enter hall number: ", 1, 50);
        int capacity = InputValidator.getValidInt(
                "Enter seat capacity: ", 1, 500);

        adminService.addHallToTheater(
                theater, hallNumber, capacity, theaterRepo, theaters);

        System.out.println("Hall added successfully.");
    }

    /*
     * ================================
     * MOVIE MANAGEMENT
     * ================================
     */
    public static void handleAddMovie(
            List<Movie> movies,
            MovieRepository movieRepo,
            AdminService adminService) {
        System.out.println("\n--- ADD MOVIE ---");

        String name = InputValidator.getRequiredString("Enter movie name: ");
        String genre = InputValidator.getOptionalString("Enter genre: ");
        genre = genre.isEmpty() ? "unknown" : genre;
        int duration = InputValidator.getValidInt(
                "Enter duration (minutes): ", 1, 500);

        Movie movie = adminService.addMovie(
                movies, movieRepo, name, genre, duration);

        System.out.println("Movie added successfully:");
        System.out.println(movie);
    }

    public Movie addMovie(
            List<Movie> movies,
            MovieRepository movieRepo,
            String name,
            String genre,
            int duration) {
        int id = movieRepo.getNextMovieId(movies);
        Movie movie = new Movie(id, name, genre, duration);

        movies.add(movie);
        movieRepo.saveMovies(movies);

        return movie;
    }

    /*
     * ================================
     * THEATER MANAGEMENT
     * ================================
     */
    public static void handleAddTheater(
            List<Theater> theaters,
            TheaterRepository theaterRepo,
            AdminService adminService) {
        System.out.println("\n--- ADD THEATER ---");

        String name = InputValidator.getRequiredString("Enter theater name: ");
        String location = InputValidator.getRequiredString("Enter location: ");

        Theater theater = adminService.addTheater(
                theaters, theaterRepo, name, location);

        System.out.println("Theater added successfully:");
        System.out.println(theater);
    }

    public Theater addTheater(
            List<Theater> theaters,
            TheaterRepository theaterRepo,
            String name,
            String location) {
        int id = theaterRepo.getNextTheaterId(theaters);
        Theater theater = new Theater(id, name, location);

        theaters.add(theater);
        theaterRepo.saveTheaters(theaters);

        return theater;
    }

    public Hall addHallToTheater(
            Theater theater,
            int hallNumber,
            int capacity,
            TheaterRepository theaterRepo,
            List<Theater> theaters) {
        Hall hall = new Hall(hallNumber, capacity);
        theater.addHall(hall);

        theaterRepo.saveTheaters(theaters);
        return hall;
    }

    /*
     * ================================
     * SHOWTIME MANAGEMENT
     * ================================
     */
    public static void handleScheduleShowtime(
            List<Movie> movies,
            List<Theater> theaters,
            ShowtimeRepository showtimeRepo,
            AdminService adminService) {
        System.out.println("\n--- SCHEDULE SHOWTIME ---");

        if (movies.isEmpty() || theaters.isEmpty()) {
            System.out.println("Movies or theaters not available.");
            return;
        }

        for (Movie m : movies) {
            System.out.println(m.getMovieId() + " - " + m.getName());
        }
        int movieId = InputValidator.getValidInt(
                "Select Movie ID: ", 1, Integer.MAX_VALUE);

        Movie movie = movies.stream()
                .filter(m -> m.getMovieId() == movieId)
                .findFirst()
                .orElse(null);

        if (movie == null) {
            System.out.println("Movie not found.");
            return;
        }

        for (Theater t : theaters) {
            System.out.println(t.getTheaterId() + " - " + t.getName());
        }
        int theaterId = InputValidator.getValidInt(
                "Select Theater ID: ", 1, Integer.MAX_VALUE);

        Theater theater = theaters.stream()
                .filter(t -> t.getTheaterId() == theaterId)
                .findFirst()
                .orElse(null);

        if (theater == null || theater.getHalls().isEmpty()) {
            System.out.println("Invalid theater or no halls.");
            return;
        }

        for (Hall h : theater.getHalls()) {
            System.out.println("Hall " + h.getHallNumber());
        }
        int hallNumber = InputValidator.getValidInt(
                "Select Hall Number: ", 1, 50);

        Hall hall = theater.getHalls().stream()
                .filter(h -> h.getHallNumber() == hallNumber)
                .findFirst()
                .orElse(null);

        if (hall == null) {
            System.out.println("Hall not found.");
            return;
        }
        String input = InputValidator.getOptionalString("Enter showtime (yyyy-MM-ddTHH:mm) [press Enter for now]: ");
        String dateTime = input.isEmpty() ? LocalDateTime.now().toString() : input;

        adminService.scheduleShowtime(
                theaters,
                showtimeRepo,
                movie,
                theater,
                hall,
                java.time.LocalDateTime.parse(dateTime));

        System.out.println("Showtime scheduled successfully.");
    }

    public Showtime scheduleShowtime(
            List<Theater> theaters,
            ShowtimeRepository showtimeRepo,
            Movie movie,
            Theater theater,
            Hall hall,
            LocalDateTime startTime) {
        int id = showtimeRepo.getNextShowtimeId(theaters);

        Showtime showtime = new Showtime(
                id,
                movie,
                hall,
                startTime);

        hall.addShowtime(showtime);
        showtimeRepo.saveShowtimes(theaters);

        return showtime;
    }

    /*
     * ================================
     * SYSTEM SUMMARY (ADMIN VIEW)
     * ================================
     */
    public static void handleSystemSummary(
            List<Movie> movies,
            List<Theater> theaters,
            AdminService adminService) {
        AdminService.SystemSummary summary = adminService.getSystemSummary(movies, theaters);

        System.out.println("\n--- SYSTEM SUMMARY ---");
        System.out.println("Movies: " + summary.movieCount);
        System.out.println("Theaters: " + summary.theaterCount);
        System.out.println("Halls: " + summary.hallCount);
        System.out.println("Showtimes: " + summary.showtimeCount);
        return;
    }

    public SystemSummary getSystemSummary(
            List<Movie> movies,
            List<Theater> theaters) {
        int hallCount = 0;
        int showtimeCount = 0;

        for (Theater t : theaters) {
            hallCount += t.getHalls().size();
            for (Hall h : t.getHalls()) {
                showtimeCount += h.getShowtimes().size();
            }
        }

        return new SystemSummary(
                movies.size(),
                theaters.size(),
                hallCount,
                showtimeCount);
    }

    /*
     * ================================
     * SUPPORT DTO (ADMIN ONLY)
     * ================================
     */

    public static class SystemSummary {
        public final int movieCount;
        public final int theaterCount;
        public final int hallCount;
        public final int showtimeCount;

        public SystemSummary(
                int movieCount,
                int theaterCount,
                int hallCount,
                int showtimeCount) {
            this.movieCount = movieCount;
            this.theaterCount = theaterCount;
            this.hallCount = hallCount;
            this.showtimeCount = showtimeCount;
        }
    }
}
