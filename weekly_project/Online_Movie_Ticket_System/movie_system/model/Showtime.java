package movie_system.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Showtime {

    private final Movie movie;
    private final Hall hall;
    private final LocalDateTime startTime;
    private final List<Seat> seats;

    public Showtime(Movie movie, Hall hall, LocalDateTime startTime) {
        if (movie == null) {
            throw new IllegalArgumentException("Movie must not be null");
        }
        if (hall == null) {
            throw new IllegalArgumentException("Hall must not be null");
        }
        if (startTime == null) {
            throw new IllegalArgumentException("Start time must not be null");
        }

        this.movie = movie;
        this.hall = hall;
        this.startTime = startTime;
        this.seats = initializeSeats(hall.getCapacity());
    }

    private List<Seat> initializeSeats(int capacity) {
        List<Seat> seatList = new ArrayList<>();
        for (int i = 1; i <= capacity; i++) {
            seatList.add(new Seat(i));
        }
        return seatList;
    }

    public Movie getMovie() {
        return movie;
    }

    public Hall getHall() {
        return hall;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Returns all seats for this showtime.
     */
    public List<Seat> getSeats() {
        return Collections.unmodifiableList(seats);
    }

    /**
     * Returns only available (not booked) seats.
     */
    public List<Seat> getAvailableSeats() {
        List<Seat> available = new ArrayList<>();
        for (Seat seat : seats) {
            if (!seat.isBooked()) {
                available.add(seat);
            }
        }
        return available;
    }

    /**
     * Books a specific seat number.
     * Booking logic will later be delegated to BookingService,
     * but this method enforces seat-level state safety.
     */
    public void bookSeat(int seatNumber) {
        if (seatNumber <= 0 || seatNumber > seats.size()) {
            throw new IllegalArgumentException("Invalid seat number");
        }

        Seat seat = seats.get(seatNumber - 1);
        if (seat.isBooked()) {
            throw new IllegalStateException("Seat " + seatNumber + " is already booked");
        }

        seat.book();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Showtime)) return false;
        Showtime showtime = (Showtime) o;
        return movie.equals(showtime.movie) &&
               hall.equals(showtime.hall) &&
               startTime.equals(showtime.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movie, hall, startTime);
    }

    @Override
    public String toString() {
        return "Showtime{" +
                "movie=" + movie.getName() +
                ", hall=" + hall.getHallNumber() +
                ", startTime=" + startTime +
                '}';
    }
}
