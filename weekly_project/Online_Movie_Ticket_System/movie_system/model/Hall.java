package movie_system.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Hall {

    private final int hallNumber;
    private final int capacity;
    private final List<Showtime> showtimes;

    public Hall(int hallNumber, int capacity) {
        if (hallNumber <= 0) {
            throw new IllegalArgumentException("Hall number must be positive");
        }
        if (capacity <= 0) {
            throw new IllegalArgumentException("Hall capacity must be greater than zero");
        }
        this.hallNumber = hallNumber;
        this.capacity = capacity;
        this.showtimes = new ArrayList<>();
    }

    public int getHallNumber() {
        return hallNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    /**
     * Adds a showtime to this hall.
     * Ensures no two showtimes overlap in time.
     */
    public void addShowtime(Showtime showtime) {
        if (showtime == null) {
            throw new IllegalArgumentException("Showtime must not be null");
        }

        // Enforce scheduling constraint: only one show at a time in this hall
        for (Showtime existing : showtimes) {
            if (existing.getStartTime().equals(showtime.getStartTime())) {
                throw new IllegalArgumentException(
                        "Hall " + hallNumber + " already has a show at " + showtime.getStartTime());
            }
        }

        showtimes.add(showtime);
    }

    /**
     * Returns all showtimes in this hall.
     */
    public List<Showtime> getShowtimes() {
        return Collections.unmodifiableList(showtimes);
    }

    /**
     * Finds a showtime by its start time.
     */
    public Showtime getShowtimeAt(LocalDateTime startTime) {
        for (Showtime showtime : showtimes) {
            if (showtime.getStartTime().equals(startTime)) {
                return showtime;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Hall))
            return false;
        Hall hall = (Hall) o;
        return hallNumber == hall.hallNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hallNumber);
    }

    @Override
    public String toString() {
        return "Hall{" +
                "hallNumber=" + hallNumber +
                ", capacity=" + capacity +
                '}';
    }
}
