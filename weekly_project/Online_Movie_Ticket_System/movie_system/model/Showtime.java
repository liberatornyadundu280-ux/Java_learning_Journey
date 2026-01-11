package movie_system.model;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Showtime {

    private final int showtimeId;
    private final Movie movie;
    private final Hall hall;
    private final LocalDateTime startTime;
    private final List<Seat> seats;

    public Showtime(int showtimeId, Movie movie, Hall hall, LocalDateTime startTime) {
        if (showtimeId <= 0)
            throw new IllegalArgumentException("Invalid showtime ID");
        if (movie == null || hall == null || startTime == null)
            throw new IllegalArgumentException("Invalid showtime data");

        this.showtimeId = showtimeId;
        this.movie = movie;
        this.hall = hall;
        this.startTime = startTime;
        this.seats = initializeSeats(hall.getCapacity());
    }

    public int getShowtimeId() {
        return showtimeId;
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

    public List<Seat> getAvailableSeats() {
        return seats.stream()
                .filter(seat -> !seat.isBooked())
                .collect(Collectors.toList());
    }

    public List<Seat> getSeats() {
        return Collections.unmodifiableList(seats);
    }

    private List<Seat> initializeSeats(int capacity) {
        List<Seat> list = new ArrayList<>();
        for (int i = 1; i <= capacity; i++) {
            list.add(new Seat(i));
        }
        return list;
    }

    public void restoreSeat(int seatNumber, boolean booked) {
        Seat seat = seats.get(seatNumber - 1);
        if (booked)
            seat.book();
    }

    public Seat bookSeat(int seatNumber) {
        for (Seat seat : seats) {
            if (seat.getSeatNumber() == seatNumber) {
                if (seat.isBooked()) {
                    throw new IllegalStateException("Seat already booked");
                }
                seat.book();
                return seat;
            }
        }
        throw new IllegalArgumentException("Seat not found");
    }

    public void unbookSeat(int seatNumber) {
        for (Seat seat : seats) {
            if (seat.getSeatNumber() == seatNumber) {
                seat.unbook();
                return;
            }
        }
        throw new IllegalArgumentException("Seat not found");
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Showtime s) && s.showtimeId == showtimeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(showtimeId);
    }
}
