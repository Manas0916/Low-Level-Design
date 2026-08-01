

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
public class Client extends  Thread {
    private final RateLimiter rateLimiter;
    private final String name;
    private int requestId = 1;

    Client(RateLimiter rateLimiter, String name) {
        super();
        this.setDaemon(true);
        this.rateLimiter = rateLimiter;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (this.rateLimiter.allowRequest(new Request(this.requestId++, this.name))) {
                    System.out.println(this.name + " -> Allowed");
                } else {
                    System.out.println(this.name + " -> Blocked");
                }
                TimeUnit.MILLISECONDS.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(this.name + " interrupted: " + e.getMessage());
        }
    }

}
