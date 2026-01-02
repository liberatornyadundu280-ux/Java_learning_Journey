package movie_system;

import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

public class Theater {
    private String name;
    private String location;
    private Set<Showtime> showtimes;

    public Theater(String name, String location) {
        this.name = name;
        this.location = location;
        this.showtimes = new HashSet<>();
    }

    public void addShowtime(Showtime showtime) {
        this.showtimes.add(showtime);
    }

    public Set<Showtime> getShowtimes() {
        return showtimes;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Theater theater = (Theater) o;
        return name.equals(theater.name) && location.equals(theater.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, location);
    }
}