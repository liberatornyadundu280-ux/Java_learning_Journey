package movie_system;

import movie_system.model.*;
import movie_system.service.BookingService;

import java.time.LocalDateTime;
import java.util.List;

public class MovieSystem {

    public static void main(String[] args) {

        // =========================
        // 1. SYSTEM INITIALIZATION
        // =========================

        // Create movies
        Movie movie1 = new Movie("Inception", "Sci-Fi", 148);
        Movie movie2 = new Movie("Interstellar", "Sci-Fi", 169);

        // Create theater
        Theater theater = new Theater("Cineplex", "City Center");

        // Create halls
        Hall hall1 = new Hall(1, 10); // 10 seats
        Hall hall2 = new Hall(2, 8); // 8 seats

        theater.addHall(hall1);
        theater.addHall(hall2);

        // Create showtimes (1 hour from now, safe for cancellation demo)
        LocalDateTime showTime1 = LocalDateTime.now().plusHours(1);
        LocalDateTime showTime2 = LocalDateTime.now().plusHours(2);

        Showtime showtime1 = new Showtime(movie1, hall1, showTime1);
        Showtime showtime2 = new Showtime(movie2, hall2, showTime2);

        hall1.addShowtime(showtime1);
        hall2.addShowtime(showtime2);

        System.out.println("System initialized successfully.\n");

        // =========================
        // 2. BOOKING
        // =========================

        BookingService bookingService = new BookingService();

        System.out.println("Available seats before booking: "
                + showtime1.getAvailableSeats().size());

        // Book seats 1, 2, 3
        Ticket ticket = bookingService.bookSeats(showtime1, List.of(1, 2, 3));

        System.out.println("Ticket booked successfully:");
        System.out.println(ticket);

        System.out.println("Available seats after booking: "
                + showtime1.getAvailableSeats().size() + "\n");

        // =========================
        // 3. CANCELLATION
        // =========================

        System.out.println("Attempting cancellation...\n");

        bookingService.cancelTicket(ticket);

        System.out.println("Ticket cancelled successfully:");
        System.out.println(ticket);

        System.out.println("Available seats after cancellation: "
                + showtime1.getAvailableSeats().size());
    }
}
