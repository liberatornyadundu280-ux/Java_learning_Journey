package movie_system.model;

public class Seat {

    private final int seatNumber;
    private boolean booked;

    public Seat(int seatNumber) {
        if (seatNumber <= 0) {
            throw new IllegalArgumentException("Seat number must be positive");
        }
        this.seatNumber = seatNumber;
        this.booked = false;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public boolean isBooked() {
        return booked;
    }

    public void book() {
        if (booked) {
            throw new IllegalStateException("Seat " + seatNumber + " is already booked");
        }
        this.booked = true;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "seatNumber=" + seatNumber +
                ", booked=" + booked +
                '}';
    }
}
