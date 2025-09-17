package AirlineManagementSystem.Payment;

import java.util.UUID;

public class Payment {
    private final UUID paymentId;
    private final String paymentMethod;
    private final double amount;
    private PaymentStatus paymentStatus;

    public Payment(String paymentMethod, double amount) {
        this.paymentId = UUID.randomUUID();
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.paymentStatus = PaymentStatus.PENDING;
    }

    public void processPayment(){
        System.out.println("Payement Processesing with amount = " + this.amount + " Using =  " + this.paymentMethod);
        paymentStatus = PaymentStatus.COMPLETED;
    }

    public void refundPayment() {
        System.out.println("Payement Refund with amount = " + this.amount);
        paymentStatus = PaymentStatus.REFUNDED;
    }


}
