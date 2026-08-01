package src.Client;

import src.RateLimiter;
import src.Request.Request;

import java.util.concurrent.TimeUnit;
public class Client implements Runnable {
    private final RateLimiter rateLimiter;
    private final String name;
    private int requestId = 1;


    public Client(RateLimiter rateLimiter, String name) {
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
