package AirlineManagementSystem.Aircraft;

import java.util.UUID;

public class Aircraft {
    private final UUID id;
    private final String model;
    private final Long totalSeats;

    public UUID getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public Long getTotalSeats() {
        return totalSeats;
    }

    public Aircraft(String model, Long totalSeats) {
        this.id = UUID.randomUUID();
        this.model = model;
        this.totalSeats = totalSeats;
    }
}
