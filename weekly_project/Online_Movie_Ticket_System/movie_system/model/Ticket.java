package movie_system.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Ticket {

    private final String ticketId;
    private final Showtime showtime;
    private final List<Integer> seats;
    private final LocalDateTime bookingTime;
    private boolean cancelled;

    // ADD this constructor to Ticket
    public Ticket(Showtime showtime, List<Integer> seats) {
        this.ticketId = UUID.randomUUID().toString();
        this.showtime = showtime;
        this.seats = seats;
        this.bookingTime = LocalDateTime.now();
        this.cancelled = false;
    }

    public Ticket(String Id, Showtime showtime, List<Integer> seats, LocalDateTime bookingTime, boolean cancelled) {
        this.ticketId = Id;
        this.showtime = showtime;
        this.seats = seats;
        this.bookingTime = bookingTime;
        this.cancelled = cancelled;
    }

    public String getTicketId() {
        return ticketId;
    }

    public Showtime getShowtime() {
        return showtime;
    }

    public List<Integer> getSeats() {
        return seats;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * Marks this ticket as cancelled.
     * Called only by BookingService.
     */
    public void cancel() {
        if (cancelled) {
            throw new IllegalStateException("Ticket is already cancelled");
        }
        this.cancelled = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Ticket))
            return false;
        Ticket ticket = (Ticket) o;
        return ticketId.equals(ticket.ticketId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId='" + ticketId + '\'' +
                ", movie=" + showtime.getMovie().getName() +
                ", hall=" + showtime.getHall().getHallNumber() +
                ", startTime=" + showtime.getStartTime() +
                ", seats=" + seats.size() +
                ", cancelled=" + cancelled +
                '}';
    }
}
