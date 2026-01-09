package movie_system.persistence;

import movie_system.model.Movie;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MovieRepository {

    private static final String FILE_PATH = "data/movies.txt";

    public List<Movie> loadMovies() {
        List<Movie> movies = new ArrayList<>();

        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return movies; // first run, no data yet
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String genre = parts[2];
                int duration = Integer.parseInt(parts[3]);

                movies.add(new Movie(id, name, genre, duration));
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load movies", e);
        }

        return movies;
    }

    public void saveMovies(List<Movie> movies) {
        new File("data").mkdirs(); // ensure folder exists

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Movie movie : movies) {
                writer.write(
                        movie.getMovieId() + "|" +
                                movie.getName() + "|" +
                                movie.getGenre() + "|" +
                                movie.getDuration());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to save movies", e);
        }
    }

    public int getNextMovieId(List<Movie> movies) {
        return movies.stream()
                .mapToInt(Movie::getMovieId)
                .max()
                .orElse(0) + 1;
    }
}
