package movie_system.persistence;

import movie_system.model.Seat;
import movie_system.model.Showtime;
import movie_system.model.Ticket;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class TicketRepository {

    private static final String FILE_PATH = "data/tickets.txt";

    public List<Ticket> loadTickets(List<Showtime> showtimes) {
        Map<Integer, Showtime> showtimeMap = new HashMap<>();
        for (Showtime s : showtimes) {
            showtimeMap.put(s.getShowtimeId(), s);
        }

        List<Ticket> tickets = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists())
            return tickets;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] p = line.split("\\|");

                String ticketId = p[0];
                int showtimeId = Integer.parseInt(p[1]);
                String[] seatNums = p[2].split(",");
                LocalDateTime bookingTime = LocalDateTime.parse(p[3]);
                boolean cancelled = Boolean.parseBoolean(p[4]);

                Showtime showtime = showtimeMap.get(showtimeId);
                if (showtime == null)
                    continue;

                List<Integer> seats = new ArrayList<>();
                for (String s : seatNums) {
                    int seatNo = Integer.parseInt(s);
                    seats.add(seatNo);
                }

                tickets.add(
                        new Ticket(ticketId, showtime, seats, bookingTime, cancelled));
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load tickets", e);
        }

        return tickets;
    }

    public void saveTickets(List<Ticket> tickets) {
        new File("data").mkdirs();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Ticket t : tickets) {
                String seatNumbers = t.getSeats().stream().map(String::valueOf).reduce((a, b) -> a + "," + b)
                        .orElse("");

                writer.write(
                        t.getTicketId() + "|" +
                                t.getShowtime().getShowtimeId() + "|" +
                                seatNumbers + "|" +
                                t.getBookingTime() + "|" +
                                t.isCancelled());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to save tickets", e);
        }
    }

}
