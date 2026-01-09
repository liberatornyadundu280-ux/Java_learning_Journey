package movie_system.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Theater {

    private final String name;
    private final String location;
    private final List<Hall> halls;

    public Theater(String name, String location) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Theater name must not be null or blank");
        }
        if (location == null || location.isBlank()) {
            throw new IllegalArgumentException("Theater location must not be null or blank");
        }
        this.name = name;
        this.location = location;
        this.halls = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    /**
     * Adds a hall to this theater.
     * Duplicate halls are not allowed.
     */
    public void addHall(Hall hall) {
        if (hall == null) {
            throw new IllegalArgumentException("Hall must not be null");
        }
        if (halls.contains(hall)) {
            throw new IllegalArgumentException("Duplicate hall is not allowed");
        }
        halls.add(hall);
    }

    /**
     * Returns an unmodifiable list of halls.
     */
    public List<Hall> getHalls() {
        return Collections.unmodifiableList(halls);
    }

    /**
     * Finds a hall by its identifier (e.g., hall number).
     */
    public Hall getHallByNumber(int hallNumber) {
        for (Hall hall : halls) {
            if (hall.getHallNumber() == hallNumber) {
                return hall;
            }
        }
        return null; // handled by caller
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Theater))
            return false;
        Theater theater = (Theater) o;
        return name.equals(theater.name) &&
                location.equals(theater.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, location);
    }

    @Override
    public String toString() {
        return "Theater{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
