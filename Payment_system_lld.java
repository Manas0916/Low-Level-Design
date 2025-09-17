import java.time.Instant;
import java.util.*;
import java.math.BigDecimal;
import java.util.UUID;

enum PaymentState {NEW, AUTHORIZED, FAILED, REFUNDED, CAPTURED}
enum PaymentMethodType {DEBIT_CARD, UPI, CREDIT_CARD }

record PaymentRequest (
    String orderId,
    PaymentMethodType methodType,
    double amount
){}

interface PaymentGateway {
    
    public String authorize(PaymentRequest request);
    public String capture(double amount);
    public boolean refund( String paymentId, double amount);
    
}

class DebitCardGateway implements PaymentGateway {
    @Override
    public String authorize(PaymentRequest request) {
        String orderId = request.orderId();
        String authId = "AUTH_DB_" + orderId;
        if (orderId == null) {
            System.out.println("Payement Id cannot be null");
            return null;
            
        }
        if(request.amount() < 0 ) {
            System.out.println("amount cannot be less than 0");
            return null;
        }
        return authId;
    }
    
    @Override
    public String capture(double amount) {
        if(amount > 0) {
            String paymentId = "PAY_DB_" + UUID.randomUUID();
            return paymentId;
        }
        return null;
    }
    
     @Override 
     public boolean refund(String paymentId, double amount) {
        String refundId = "RF_DB_" + UUID.randomUUID();
        System.out.println("refrunding amount" + amount + "Payment Id = " + paymentId);
        return true;
    }
}

class UpiGateway implements PaymentGateway {
     @Override
    public String authorize(PaymentRequest request) {
        String orderId = request.orderId();
        String authId = "AUTH_UPI_" + orderId;
        if (orderId == null) {
            System.out.println("Payement Id cannot be null");
            return null;
        }
        if(request.amount() < 0 ) {
            System.out.println("Amount cannot be less than or equal to 0");
            return null;
        }
        return authId;
    }
    
    @Override
    public String capture(double amount) {
        if(amount > 0){
            String paymentId = "PAY_UPI_" + UUID.randomUUID();
            return paymentId;
        }
        return null;
    }
    
     @Override 
     public boolean refund(String paymentId, double amount) {
        String refundId = "RF_UPI_" + UUID.randomUUID();
        System.out.println("refrunding amount" + amount + "Payment Id = " + paymentId);
        return true;
    }
}

class CreditCardGateway implements PaymentGateway {
    @Override
    public String authorize(PaymentRequest request) {
        String orderId = request.orderId();
        String authId = "AUTH_CC_" + orderId;
        if(orderId == null) {
            System.out.println("Payement Id cannot be null");
            return null;
        }
        if(request.amount() < 0) {
            System.out.println("Amount cannot be less than or equal to 0");
            return null;
        }
        System.out.println("Authorization successfull");
        return authId;
    }
    
    @Override
    public String capture(double amount) {
        if(amount > 0.0){
            String paymentId = "PAY_CC_" + UUID.randomUUID();
            System.out.println("Payment Processed successfully -- with Id" + paymentId);
            return paymentId;
        }
        return null;
    }
    
    @Override
    public boolean refund(String paymentId, double amount) {
        String refundId = "RF_CC_" + UUID.randomUUID();
        System.out.println("refrunding amount" + amount + "Payment Id = " + paymentId);
        return true;
    }
}


class Payment {

    private final String orderId;
    private PaymentState state = PaymentState.NEW;
    private String authorizationId;
    private String paymentId;
    private final double amount;
    private final Instant createdAt = Instant.now();
    
    public Payment(String _orderId, double _amount) {
        this.orderId = _orderId;
        this.amount = _amount;
    }
    
    public PaymentState state() { return this.state; }
    public String authorizationId() { return this.authorizationId; }
    public String paymentId() { return this.paymentId; }
    
    public void onAuthorized(String authId){
        ensure(this.state == PaymentState.NEW);
        this.authorizationId = authId;
        this.state = PaymentState.AUTHORIZED;
    }
    
    public void onCapture(String PaymentId){
        ensure(this.state == PaymentState.AUTHORIZED);
        this.paymentId = paymentId;
        this.state = PaymentState.CAPTURED;
    }
    
    public void onRefunded(String refundId) {
        ensure(state == PaymentState.CAPTURED);
        this.state = PaymentState.REFUNDED;
    }
    
    public void onFailed() {
        this.state = PaymentState.FAILED;
    }
    
    
    private static void ensure(boolean cond) {
        if (!cond) throw new IllegalStateException("Invalid payment state transition");
    }
}

class PaymentGatewayFactory {
     private final Map<PaymentMethodType, PaymentGateway> cache = new EnumMap<>(PaymentMethodType.class);
     public PaymentGatewayFactory() {
         cache.put(PaymentMethodType.DEBIT_CARD, new DebitCardGateway());
         cache.put(PaymentMethodType.CREDIT_CARD, new CreditCardGateway());
         cache.put(PaymentMethodType.UPI, new UpiGateway());
     }
     
     public PaymentGateway get(PaymentMethodType type) {
        PaymentGateway gw = cache.get(type);
        if (gw == null) throw new IllegalArgumentException("Unsupported method: " + type);
        return gw;
    }
}




class PaymentProcessor {
    private PaymentGatewayFactory factory;
    
    PaymentProcessor(PaymentGatewayFactory fw){
        this.factory = fw;
    }
    
    public Payment process(PaymentRequest request){
        Payment payment = new Payment(request.orderId(), request.amount());
        PaymentGateway gateway = factory.get(request.methodType());
        String auth  = gateway.authorize(request);
        if(auth == null) {
            payment.onFailed();
            throw new RuntimeException("Auth failed"); 
        }
        
        payment.onAuthorized(auth);
        String paymentId = gateway.capture(request.amount());
        if(paymentId == null) {
            payment.onFailed();
            System.out.println("Payment Id is required");
            throw new RuntimeException("Payment Id is required");
        }
        payment.onCapture(paymentId);
        
        return payment;
    }
    
    private void preValidate(PaymentRequest request) {
        if (request.amount() <= 0)
            throw new IllegalArgumentException("Amount must be > 0");
        Objects.requireNonNull(request.methodType(), "Payment method required");
    }
}



public class MyClass {
  public static void main(String args[]) {
      
        PaymentGatewayFactory factory = new PaymentGatewayFactory();
        PaymentProcessor processor = new PaymentProcessor(factory);
        PaymentRequest ccReq = new PaymentRequest("ORDER-101", PaymentMethodType.CREDIT_CARD, 50.95);
        Payment ccPayment = processor.process(ccReq);
        System.out.println("Final state: " + ccPayment.state());
    
  }
}
