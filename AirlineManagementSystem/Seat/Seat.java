package AirlineManagementSystem.Seat;

public class Seat {
    private String seatNo;
    private SeatType seatType;
    private SeatStatus seatStatus;

    public Seat(String seatNo, SeatType seatType) {
        this.seatNo = seatNo;
        this.seatType = seatType;
        this.seatStatus = SeatStatus.AVAILABLE;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public SeatStatus getSeatStatus() {
        return seatStatus;
    }

    public void setSeatStatus(SeatStatus seatStatus) {
        this.seatStatus = seatStatus;
    }

    public void reserve() {
        seatStatus = SeatStatus.RESERVED;
    }

    public void release() {
        seatStatus = SeatStatus.AVAILABLE;
    }

    public synchronized boolean isBooked(){
        return seatStatus == SeatStatus.OCCUPIED;
    }
}
