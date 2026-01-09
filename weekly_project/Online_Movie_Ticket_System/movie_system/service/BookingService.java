package movie_system.service;

import movie_system.model.Seat;
import movie_system.model.Showtime;
import movie_system.model.Ticket;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class BookingService {

    // Business rule: cancellation allowed X minutes before showtime
    private static final int CANCELLATION_CUTOFF_MINUTES = 30;

    /**
     * Books seats for a given showtime and returns a ticket.
     */
    public Ticket bookSeats(Showtime showtime, List<Integer> seatNumbers) {
        if (showtime == null || seatNumbers == null || seatNumbers.isEmpty()) {
            throw new IllegalArgumentException("Invalid booking request");
        }

        // Book each seat
        for (int seatNumber : seatNumbers) {
            showtime.bookSeat(seatNumber);
        }

        return new Ticket(showtime, seatNumbers);
    }

    /**
     * Cancels a ticket if cancellation rules allow it.
     */
    public void cancelTicket(Ticket ticket) {
        if (ticket == null) {
            throw new IllegalArgumentException("Ticket must not be null");
        }
        if (ticket.isCancelled()) {
            throw new IllegalStateException("Ticket is already cancelled");
        }

        Showtime showtime = ticket.getShowtime();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime showStart = showtime.getStartTime();

        long minutesBeforeShow = Duration.between(now, showStart).toMinutes();

        if (minutesBeforeShow < CANCELLATION_CUTOFF_MINUTES) {
            throw new IllegalStateException(
                    "Cancellation not allowed within " +
                            CANCELLATION_CUTOFF_MINUTES + " minutes of showtime");
        }

        // Release seats
        for (Seat seat : ticket.getSeats()) {
            seat.unbook();
        }

        ticket.cancel();
    }
}
