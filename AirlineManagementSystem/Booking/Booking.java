package AirlineManagementSystem.Booking;

import AirlineManagementSystem.Flight.Flight;
import AirlineManagementSystem.Passenger.Passenger;
import AirlineManagementSystem.Seat.Seat;

import java.util.UUID;

public class Booking {
    private final UUID id;
    private final Flight flight;
    private final Passenger passenger;
    private final Seat seat;
    private final Double price;
    private BookingStatus bookingStatus;

    public Booking(UUID id, Flight flight, Passenger passenger, Seat seat, Double price) {
        this.id = id;
        this.flight = flight;
        this.passenger = passenger;
        this.seat = seat;
        this.price = price;
        this.bookingStatus = BookingStatus.CONFIRMED;
    }

    public UUID getId() {
        return id;
    }

    public Flight getFlight() {
        return flight;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Seat getSeat() {
        return seat;
    }

    public Double getPrice() {
        return price;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void cancel(){
        bookingStatus = BookingStatus.CANCELLED;
        seat.release();
    }
}
