package AirlineManagementSystem.Airline;

import AirlineManagementSystem.Aircraft.Aircraft;
import AirlineManagementSystem.Booking.Booking;
import AirlineManagementSystem.Booking.BookingManager;
import AirlineManagementSystem.Flight.Flight;
import AirlineManagementSystem.Passenger.Passenger;
import AirlineManagementSystem.Payment.Payment;
import AirlineManagementSystem.Payment.PaymentProcessor;
import AirlineManagementSystem.Seat.Seat;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AirlineManager {
    private final Map<UUID, Flight> flights;
    private final Map<UUID, Aircraft> aircrafts;
    private final Map<UUID, Passenger> passengers;
    private final BookingManager bookingManager;
    private final PaymentProcessor paymentProcessor;

    public AirlineManager() {
        this.flights = new HashMap<>();
        this.aircrafts = new HashMap<>();
        this.passengers = new HashMap<>();
        this.bookingManager = BookingManager.getInstance();
        this.paymentProcessor = PaymentProcessor.getInstance();
    }
    public Passenger addPassenger(String name, String email, String phn){
        Passenger passenger = new Passenger(name, email, phn);
        passengers.put(passenger.getId(), passenger);
        return passenger;
    }
    public Aircraft addAircraft(String model, Long totalSeats){
        Aircraft aircraft = new Aircraft(model, totalSeats);
        aircrafts.put(aircraft.getId(), aircraft);
        return aircraft;
    }
    public Flight addFlight(String source, String destination, LocalDateTime departure, LocalDateTime arrival, UUID aircraftNumber) {
        Aircraft aircraft = aircrafts.get(aircraftNumber);
        Flight flight = new Flight(aircraft, source, destination, departure, arrival);
        flights.put(flight.getFlightNo(), flight);
        return flight;
    }

    public Booking bookFlight(UUID flightNumber, UUID passengerId, Seat seat, double price) {
        Flight flight = flights.get(flightNumber);
        flight.reserveSeat(seat.getSeatNo());
        Passenger passenger = passengers.get(passengerId);
        return bookingManager.createBooking(flight, passenger, seat, price);
    }

    public void cancelBooking(Booking booking) {
        if(booking == null) throw new IllegalArgumentException("Booking now Found");
        Flight flight = booking.getFlight();
        flight.releaseSeat(booking.getSeat().getSeatNo());
        bookingManager.cancelBooking(booking.getId());
    }

    public void processPayment(Payment payment){
        payment.processPayment();
    }

    public void refundPayment(Payment payment){
        payment.refundPayment();
    }


}
