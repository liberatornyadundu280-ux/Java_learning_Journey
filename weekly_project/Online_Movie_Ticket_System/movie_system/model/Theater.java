package movie_system.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Theater {

    private final int theaterId;
    private final String name;
    private final String location;
    private final List<Hall> halls;

    public Theater(int theaterId, String name, String location) {
        if (theaterId <= 0) {
            throw new IllegalArgumentException("Theater ID must be positive");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Theater name must not be blank");
        }
        if (location == null || location.isBlank()) {
            throw new IllegalArgumentException("Location must not be blank");
        }

        this.theaterId = theaterId;
        this.name = name;
        this.location = location;
        this.halls = new ArrayList<>();
    }

    public int getTheaterId() {
        return theaterId;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public void addHall(Hall hall) {
        if (hall == null) {
            throw new IllegalArgumentException("Hall cannot be null");
        }
        if (halls.contains(hall)) {
            throw new IllegalArgumentException("Duplicate hall");
        }
        halls.add(hall);
    }

    public List<Hall> getHalls() {
        return Collections.unmodifiableList(halls);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Theater))
            return false;
        Theater theater = (Theater) o;
        return theaterId == theater.theaterId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(theaterId);
    }

    @Override
    public String toString() {
        return "Theater{" +
                "id=" + theaterId +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
