package AirlineManagementSystem.Flight;

import AirlineManagementSystem.Aircraft.Aircraft;
import AirlineManagementSystem.Seat.Seat;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Flight {
    private final Aircraft aircraft;
    private final UUID flightNo;
    private final String source;
    private final String destination;
    private final LocalDateTime departureTime;
    private final LocalDateTime arrivalTime;
    private final FlightStatus flightStatus;
    private final Map<String, Seat> seats;

    public Flight(Aircraft aircraft, String source, String destination, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        this.aircraft = aircraft;
        this.flightNo = UUID.randomUUID();
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.flightStatus = FlightStatus.ON_TIME;
        this.seats = new HashMap<>();
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public UUID getFlightNo() {
        return flightNo;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public FlightStatus getFlightStatus() {
        return flightStatus;
    }

    public Map<String, Seat> getSeats() {
        return seats;
    }

    public synchronized void reserveSeat(String seatNo){
        Seat seat = seats.get(seatNo);
        if(seat == null) throw new IllegalArgumentException("Invalid seat Number");
        seat.reserve();
    }
    public synchronized boolean isSeatAvailable(String seatNo){
        Seat seat = seats.get(seatNo);
        return seat != null && !seat.isBooked();
    }
    public synchronized void releaseSeat(String seatNo) {
        Seat seat = seats.get(seatNo);
        if(seat != null) seat.release();
    }

    public void addSeat(Seat seat){
        seats.put(seat.getSeatNo(), seat);
    }

}
