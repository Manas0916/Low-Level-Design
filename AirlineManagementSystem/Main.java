package AirlineManagementSystem;

import AirlineManagementSystem.Aircraft.Aircraft;
import AirlineManagementSystem.Airline.AirlineManager;
import AirlineManagementSystem.Booking.Booking;
import AirlineManagementSystem.Flight.Flight;
import AirlineManagementSystem.Passenger.Passenger;
import AirlineManagementSystem.Payment.Payment;
import AirlineManagementSystem.Seat.Seat;
import AirlineManagementSystem.Seat.SeatType;

import java.time.LocalDateTime;

public class Main {
    public static void main(String args[]) throws Exception {
        AirlineManager airlineManagementSystem = new AirlineManager();

        // Passenger
        Passenger passenger1 = new Passenger("Manas", "manas.bajpai16@gmail.com", "7991994890");
        Passenger passenger2 = new Passenger("Abhay", "abhay@email.com", "9453286105");

        //create aircrafts
        Aircraft aircraft1 = airlineManagementSystem.addAircraft("Boeing 747", 300L);
        Aircraft aircraft2 = airlineManagementSystem.addAircraft("Airbus A380", 500L);

        // Create flights
        LocalDateTime departureTime1 = LocalDateTime.now().plusDays(1);
        LocalDateTime arrivalTime1 = departureTime1.plusHours(2);
        LocalDateTime departureTime2 = LocalDateTime.now().plusDays(3);
        LocalDateTime arrivalTime2 = LocalDateTime.now().plusDays(5);
        Flight flight1 = airlineManagementSystem.addFlight("New York", "London", departureTime1, arrivalTime1, aircraft1.getId());
        Flight flight2 = airlineManagementSystem.addFlight("India", "London", departureTime2, arrivalTime2, aircraft2.getId());


        //Creat seat
        Seat seat25A = new Seat("25A", SeatType.ECONOMY);
        Seat seat26A = new Seat("26A", SeatType.BUSINESS);

        flight1.addSeat(new Seat("25A", SeatType.ECONOMY));
        flight1.addSeat(new Seat("26A", SeatType.BUSINESS));

        flight2.addSeat(new Seat("26A", SeatType.BUSINESS));
        flight2.addSeat(new Seat("25A", SeatType.ECONOMY));

        // Book a flight
        Booking booking = airlineManagementSystem.bookFlight(flight1.getFlightNo(), passenger1.getId(), new Seat("25A", SeatType.ECONOMY), 2500);
        if (booking != null) {
            System.out.println("Booking successful. Booking ID: " + booking.getId());
            airlineManagementSystem.processPayment(new Payment("UPI", booking.getPrice()));
        } else {
            System.out.println("Booking failed.");
        }

        // Cancel a booking
        airlineManagementSystem.cancelBooking(booking);
        airlineManagementSystem.refundPayment(new Payment("UPI", booking.getPrice()));
        System.out.println("Booking cancelled.");
    }
}
