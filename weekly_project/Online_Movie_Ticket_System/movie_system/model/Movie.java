package movie_system.model;

import java.util.Objects;

public final class Movie {

    private final int movieId;
    private final String name;
    private final String genre;
    private final int duration;

    public Movie(int movieId, String name, String genre, int duration) {
        if (movieId <= 0) {
            throw new IllegalArgumentException("Movie ID must be positive");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Movie name must not be blank");
        }
        if (genre == null || genre.isBlank()) {
            throw new IllegalArgumentException("Genre must not be blank");
        }
        if (duration <= 0) {
            throw new IllegalArgumentException("Duration must be positive");
        }

        this.movieId = movieId;
        this.name = name;
        this.genre = genre;
        this.duration = duration;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Movie))
            return false;
        Movie movie = (Movie) o;
        return movieId == movie.movieId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + movieId +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", duration=" + duration +
                '}';
    }
}
