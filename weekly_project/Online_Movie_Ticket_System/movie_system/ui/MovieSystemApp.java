package movie_system.ui;

import movie_system.model.*;
import movie_system.persistence.*;
import movie_system.service.AdminService;
import movie_system.service.BookingService;
import movie_system.utils.InputValidator;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class MovieSystemApp {

    private static void adminMenu(
            List<Movie> movies,
            List<Theater> theaters,
            MovieRepository movieRepo,
            TheaterRepository theaterRepo,
            ShowtimeRepository showtimeRepo,
            AdminService adminService) {
        boolean inAdmin = true;

        while (inAdmin) {
            System.out.println("\n--- ADMIN MENU ---");
            System.out.println("1. Add Movie");
            System.out.println("2. Add Theater");
            System.out.println("3. Add Hall to Theater");
            System.out.println("4. Schedule Showtime");
            System.out.println("5. View System Summary");
            System.out.println("6. Back");

            int choice = InputValidator.getValidInt(
                    "Choose option: ", 1, 6);

            switch (choice) {
                case 1 -> AdminService.handleAddMovie(movies, movieRepo, adminService);
                case 2 -> AdminService.handleAddTheater(theaters, theaterRepo, adminService);
                case 3 -> AdminService.handleAddHall(theaters, theaterRepo, adminService);
                case 4 -> AdminService.handleScheduleShowtime(movies, theaters, showtimeRepo, adminService);
                case 5 -> AdminService.handleSystemSummary(movies, theaters, adminService);
                case 6 -> inAdmin = false;
                default -> System.out.println("SYSTEM ERROR: Unexpected admin menu option.");
            }
        }
    }

    private static void customerMenu(
            List<Movie> movies,
            List<Theater> theaters,
            List<Ticket> tickets,
            BookingService bookingService,
            TicketRepository ticketRepo,
            ShowtimeRepository showtimeRepo) {

        boolean running = true;

        while (running) {
            printMenu();

            // ✅ RANGE-VALIDATED INPUT (NO INVALID STATES)
            int choice = InputValidator.getValidInt(
                    "Choose option: ", 1, 5);

            switch (choice) {
                case 1 -> listMovies(movies);
                case 2 -> listTheatersAndShowtimes(theaters);
                case 3 -> handleBooking(
                        theaters, tickets,
                        bookingService, ticketRepo, showtimeRepo);
                case 4 -> handleCancellation(
                        tickets, bookingService,
                        ticketRepo, showtimeRepo, theaters);
                case 5 -> {
                    ticketRepo.saveTickets(tickets);
                    showtimeRepo.saveShowtimes(theaters);
                    System.out.println("System closed safely.");
                    running = false;
                }
                default -> {
                    // Defensive fallback: should never happen
                    System.out.println(
                            "SYSTEM ERROR: Unexpected menu option encountered.");
                }
            }

        }
    }

    public static void main(String[] args) {

        // ---------- LOAD DATA ----------
        MovieRepository movieRepo = new MovieRepository();
        TheaterRepository theaterRepo = new TheaterRepository();
        ShowtimeRepository showtimeRepo = new ShowtimeRepository();
        TicketRepository ticketRepo = new TicketRepository();

        List<Movie> movies = movieRepo.loadMovies();
        List<Theater> theaters = theaterRepo.loadTheaters();
        List<Showtime> showtimes = showtimeRepo.loadShowtimes(movies, theaters);
        List<Ticket> tickets = ticketRepo.loadTickets(showtimes);

        BookingService bookingService = new BookingService();
        AdminService adminService = new AdminService();

        boolean running = true;

        /*
         * =========================
         * MAIN MENU LOOP
         * =========================
         */

        while (running) {
            printMainMenu();

            int choice = InputValidator.getValidInt(
                    "Choose option: ", 1, 3);

            switch (choice) {

                case 1 -> customerMenu(
                        movies,
                        theaters,
                        tickets,
                        bookingService,
                        ticketRepo,
                        showtimeRepo);

                case 2 -> adminMenu(
                        movies,
                        theaters,
                        movieRepo,
                        theaterRepo,
                        showtimeRepo,
                        adminService);

                case 3 -> {
                    // Final persistence safeguard
                    ticketRepo.saveTickets(tickets);
                    showtimeRepo.saveShowtimes(theaters);

                    System.out.println("System exited safely.");
                    running = false;
                }

                default -> {
                    // Defensive fallback (should never happen)
                    System.out.println(
                            "SYSTEM ERROR: Invalid main menu state.");
                }
            }
        }
    }
    /*
     * =========================
     * MAIN MENU DISPLAY
     * =========================
     */

    private static void printMainMenu() {
        System.out.println("\n==================================");
        System.out.println(" ONLINE MOVIE TICKET SYSTEM ");
        System.out.println("==================================");
        System.out.println("1. Customer");
        System.out.println("2. Admin");
        System.out.println("3. Exit");
    }
    // ---------------- MENU ----------------

    private static void printMenu() {
        System.out.println("\n==================================");
        System.out.println(" ONLINE MOVIE TICKET SYSTEM ");
        System.out.println("==================================");
        System.out.println("1. List Movies");
        System.out.println("2. List Theaters & Showtimes");
        System.out.println("3. Book Ticket");
        System.out.println("4. Cancel Ticket");
        System.out.println("5. Exit");
    }

    // ---------------- LISTING ----------------

    private static void listMovies(List<Movie> movies) {
        if (movies.isEmpty()) {
            System.out.println("No movies available.");
            return;
        }
        System.out.println("\nAVAILABLE MOVIES");
        System.out.println("----------------------------------------------------------------");
        System.out.printf("%-5s %-25s %-15s %-10s%n",
                "ID", "Movie Name", "Genre", "Duration");
        System.out.println("----------------------------------------------------------------");

        int id = 1;
        for (Movie m : movies) {
            System.out.printf("%-5d %-25s %-15s %-10d%n",
                    id++,
                    m.getName(),
                    m.getGenre(),
                    m.getDuration());
        }

        System.out.println("----------------------------------------------------------------");

    }

    private static void listTheatersAndShowtimes(List<Theater> theaters) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for (Theater t : theaters) {
            System.out.println("\n" + t.getName() + " (" + t.getLocation() + ")");
            for (Hall h : t.getHalls()) {
                System.out.println("  Hall " + h.getHallNumber());
                for (Showtime s : h.getShowtimes()) {
                    System.out.println(
                            "    Showtime ID: " + s.getShowtimeId()
                                    + " | Movie: " + s.getMovie().getName()
                                    + " | Time: " + s.getStartTime().format(fmt)
                                    + " | Available seats: " + s.getAvailableSeats().size());
                }
            }
        }
    }

    // ---------------- BOOKING ----------------

    private static void handleBooking(
            List<Theater> theaters,
            List<Ticket> tickets,
            BookingService bookingService,
            TicketRepository ticketRepo,
            ShowtimeRepository showtimeRepo) {
        int showtimeId = InputValidator.getValidInt(
                "Enter Showtime ID: ", 1, Integer.MAX_VALUE);

        Showtime showtime = findShowtimeById(theaters, showtimeId);
        if (showtime == null) {
            System.out.println("Showtime not found.");
            return;
        }

        System.out.println("Available seats:");
        for (Seat seat : showtime.getAvailableSeats()) {
            System.out.print(seat.getSeatNumber() + " ");
        }
        System.out.println();

        List<Integer> seatNumbers = InputValidator.getIntList(
                "Enter seat numbers (comma separated): ");

        Ticket ticket = bookingService.bookSeats(showtime, seatNumbers);
        tickets.add(ticket);

        ticketRepo.saveTickets(tickets);
        showtimeRepo.saveShowtimes(theaters);

        System.out.println("Booking successful!");
        System.out.println("Your Ticket ID: " + ticket.getTicketId());
    }

    // ---------------- CANCELLATION ----------------

    private static void handleCancellation(
            List<Ticket> tickets,
            BookingService bookingService,
            TicketRepository ticketRepo,
            ShowtimeRepository showtimeRepo,
            List<Theater> theaters) {
        String ticketId = InputValidator.getRequiredString("Enter Ticket ID: ");

        Ticket ticket = tickets.stream()
                .filter(t -> t.getTicketId().equals(ticketId))
                .findFirst()
                .orElse(null);

        if (ticket == null) {
            System.out.println("Ticket not found.");
            return;
        }

        bookingService.cancelTicket(ticket);

        ticketRepo.saveTickets(tickets);
        showtimeRepo.saveShowtimes(theaters);

        System.out.println("Ticket cancelled successfully.");
    }

    // ---------------- HELPERS ----------------

    private static Showtime findShowtimeById(
            List<Theater> theaters, int id) {
        for (Theater t : theaters) {
            for (Hall h : t.getHalls()) {
                for (Showtime s : h.getShowtimes()) {
                    if (s.getShowtimeId() == id) {
                        return s;
                    }
                }
            }
        }
        return null;
    }
}
