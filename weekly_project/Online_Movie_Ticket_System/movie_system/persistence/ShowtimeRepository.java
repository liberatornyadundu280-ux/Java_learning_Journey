package movie_system.persistence;

import movie_system.model.*;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class ShowtimeRepository {

    private static final String SHOWTIME_FILE = "data/showtimes.txt";
    private static final String SEAT_FILE = "data/seats.txt";

    public List<Showtime> loadShowtimes(
            List<Movie> movies,
            List<Theater> theaters) {
        Map<Integer, Showtime> showtimeMap = new HashMap<>();

        // Index movies
        Map<Integer, Movie> movieMap = new HashMap<>();
        for (Movie m : movies)
            movieMap.put(m.getMovieId(), m);

        // Index halls
        Map<String, Hall> hallMap = new HashMap<>();
        for (Theater t : theaters) {
            for (Hall h : t.getHalls()) {
                hallMap.put(t.getTheaterId() + "-" + h.getHallNumber(), h);
            }
        }

        // Load showtimes
        File file = new File(SHOWTIME_FILE);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] p = line.split("\\|");

                    int showtimeId = Integer.parseInt(p[0]);
                    int movieId = Integer.parseInt(p[1]);
                    int theaterId = Integer.parseInt(p[2]);
                    int hallNumber = Integer.parseInt(p[3]);
                    LocalDateTime startTime = LocalDateTime.parse(p[4]);

                    Movie movie = movieMap.get(movieId);
                    Hall hall = hallMap.get(theaterId + "-" + hallNumber);

                    Showtime showtime = new Showtime(showtimeId, movie, hall, startTime);
                    hall.addShowtime(showtime);
                    showtimeMap.put(showtimeId, showtime);
                }
            } catch (IOException e) {
                throw new RuntimeException("Failed to load showtimes", e);
            }
        }

        // Load seats
        File seatFile = new File(SEAT_FILE);
        if (seatFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(seatFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] p = line.split("\\|");
                    int showtimeId = Integer.parseInt(p[0]);
                    int seatNumber = Integer.parseInt(p[1]);
                    boolean booked = Boolean.parseBoolean(p[2]);

                    Showtime showtime = showtimeMap.get(showtimeId);
                    if (showtime != null) {
                        showtime.restoreSeat(seatNumber, booked);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException("Failed to load seats", e);
            }
        }

        return new ArrayList<>(showtimeMap.values());
    }

    public void saveShowtimes(List<Theater> theaters) {
        new File("data").mkdirs();

        try (
                BufferedWriter showWriter = new BufferedWriter(new FileWriter(SHOWTIME_FILE));
                BufferedWriter seatWriter = new BufferedWriter(new FileWriter(SEAT_FILE))) {
            for (Theater t : theaters) {
                for (Hall h : t.getHalls()) {
                    for (Showtime s : h.getShowtimes()) {
                        showWriter.write(
                                s.getShowtimeId() + "|" +
                                        s.getMovie().getMovieId() + "|" +
                                        t.getTheaterId() + "|" +
                                        h.getHallNumber() + "|" +
                                        s.getStartTime());
                        showWriter.newLine();

                        for (Seat seat : s.getSeats()) {
                            seatWriter.write(
                                    s.getShowtimeId() + "|" +
                                            seat.getSeatNumber() + "|" +
                                            seat.isBooked());
                            seatWriter.newLine();
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to save showtimes", e);
        }
    }

    public int getNextShowtimeId(List<Theater> theaters) {
        return theaters.stream()
                .flatMap(t -> t.getHalls().stream())
                .flatMap(h -> h.getShowtimes().stream())
                .mapToInt(Showtime::getShowtimeId)
                .max()
                .orElse(0) + 1;
    }
}
