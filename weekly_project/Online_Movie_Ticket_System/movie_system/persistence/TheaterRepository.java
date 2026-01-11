package movie_system.persistence;

import movie_system.model.Hall;
import movie_system.model.Theater;

import java.io.*;
import java.util.*;

public class TheaterRepository {

    private static final String THEATER_FILE = "data/theaters.txt";
    private static final String HALL_FILE = "data/halls.txt";

    public List<Theater> loadTheaters() {
        Map<Integer, Theater> theaterMap = new HashMap<>();

        // Load theaters
        File theaterFile = new File(THEATER_FILE);
        if (theaterFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(theaterFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String location = parts[2];

                    theaterMap.put(id, new Theater(id, name, location));
                }
            } catch (IOException e) {
                throw new RuntimeException("Failed to load theaters", e);
            }
        }

        // Load halls
        File hallFile = new File(HALL_FILE);
        if (hallFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(hallFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    int theaterId = Integer.parseInt(parts[0]);
                    int hallNumber = Integer.parseInt(parts[1]);
                    int capacity = Integer.parseInt(parts[2]);

                    Theater theater = theaterMap.get(theaterId);
                    if (theater != null) {
                        theater.addHall(new Hall(hallNumber, capacity));
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException("Failed to load halls", e);
            }
        }

        return new ArrayList<>(theaterMap.values());
    }

    public void saveTheaters(List<Theater> theaters) {
        new File("data").mkdirs();

        try (
                BufferedWriter theaterWriter = new BufferedWriter(new FileWriter(THEATER_FILE));
                BufferedWriter hallWriter = new BufferedWriter(new FileWriter(HALL_FILE))) {
            for (Theater theater : theaters) {
                theaterWriter.write(
                        theater.getTheaterId() + "|" +
                                theater.getName() + "|" +
                                theater.getLocation());
                theaterWriter.newLine();

                for (Hall hall : theater.getHalls()) {
                    hallWriter.write(
                            theater.getTheaterId() + "|" +
                                    hall.getHallNumber() + "|" +
                                    hall.getCapacity());
                    hallWriter.newLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to save theaters", e);
        }
    }

    public int getNextTheaterId(List<Theater> theaters) {
        return theaters.stream()
                .mapToInt(Theater::getTheaterId)
                .max()
                .orElse(0) + 1;
    }
}
