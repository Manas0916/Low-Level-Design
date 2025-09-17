package AirlineManagementSystem.Booking;

import AirlineManagementSystem.Flight.Flight;
import AirlineManagementSystem.Passenger.Passenger;
import AirlineManagementSystem.Seat.Seat;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BookingManager {
    private static BookingManager instance;
    private final Map<UUID, Booking> bookings;
    private final Object lock = new Object();

    private BookingManager() {
        bookings = new HashMap<>();
    }

    public static synchronized BookingManager getInstance() {
        if(instance == null) {
            instance = new BookingManager();
        }
        return instance;
    }

    public Booking createBooking(Flight flight, Passenger passenger, Seat seat, double price){
        UUID bookingId = UUID.randomUUID();
        Booking booking = new Booking(bookingId, flight, passenger, seat, price);
        synchronized (lock) {
            bookings.put(bookingId, booking);
        }
        return booking;
    }

    public void cancelBooking(UUID bookingNumber){
        synchronized (lock) {
            Booking booking = bookings.get(bookingNumber);
            if (booking != null) {
                booking.cancel();
            }
        }
    }

}
