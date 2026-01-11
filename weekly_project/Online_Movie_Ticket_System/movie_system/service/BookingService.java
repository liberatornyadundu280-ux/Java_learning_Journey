package movie_system.service;

import movie_system.model.Seat;
import movie_system.model.Showtime;
import movie_system.model.Ticket;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class BookingService {

    private static final int CANCELLATION_CUTOFF_MINUTES = 30;

    public Ticket bookSeats(Showtime showtime, List<Integer> seatNumbers) {

        // LOCK on showtime (critical section)
        synchronized (showtime) {

            if (showtime == null || seatNumbers == null || seatNumbers.isEmpty()) {
                throw new IllegalArgumentException("Invalid booking request");
            }

            // 1. Validate seats
            for (int seatNumber : seatNumbers) {
                Seat seat = showtime.getSeats().get(seatNumber - 1);
                if (seat.isBooked()) {
                    throw new IllegalStateException(
                            "Seat " + seatNumber + " already booked");
                }
            }

            // 2. Book seats atomically
            for (int seatNumber : seatNumbers) {
                showtime.bookSeat(seatNumber);
            }

            return new Ticket(showtime, seatNumbers);
        }
    }

    public void cancelTicket(Ticket ticket) {

        Showtime showtime = ticket.getShowtime();

        synchronized (showtime) {

            if (ticket.isCancelled()) {
                throw new IllegalStateException("Ticket already cancelled");
            }

            // Time check
            long minutesBeforeShow = Duration.between(LocalDateTime.now(),
                    showtime.getStartTime()).toMinutes();

            if (minutesBeforeShow < CANCELLATION_CUTOFF_MINUTES) {
                throw new IllegalStateException("Cancellation window closed");
            }

            // Release seats
            for (int seatNo : ticket.getSeats()) {
                ticket.getShowtime().unbookSeat(seatNo);
            }

            ticket.cancel();
        }
    }

}
