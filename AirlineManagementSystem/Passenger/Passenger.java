package AirlineManagementSystem.Passenger;

import java.util.UUID;
public class Passenger {
    private final UUID id;
    private String name;
    private String email;
    private String phoneNumber;

    public Passenger(String _name, String _email, String _phoneNumber) {
        this.id = UUID.randomUUID();
        this.name = _name;
        this.email = _email;
        this.phoneNumber = _phoneNumber;
    }
    public UUID getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
