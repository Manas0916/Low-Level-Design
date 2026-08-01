package src.RateLimiterAlgorithms;

import src.RefillableRateLimiter;
import src.Request.Request;

import java.util.concurrent.*;

public class LeakyBucket implements RefillableRateLimiter {
    private BlockingQueue<Request> queue;
    private final int leakRate;
    private final int capacity;

    public LeakyBucket(int leakRate, int capacity) {
        this.leakRate = leakRate;
        this.capacity = capacity;
        this.queue = new LinkedBlockingQueue<>(this.capacity);
    }

    @Override
    public synchronized boolean allowRequest(Request request) {
        if(queue.offer(request)) {
            System.out.println(request + " accepted");
            return true;
        }

        System.out.println(request + " rejected");
        return false;
    }

    public void leak(int leakRate) {
        for(int i = 0; i < leakRate; i++) {
            Request req = queue.poll();

            if(req == null)
                break;
            System.out.println("Processed " + req);
        }
    }

    @Override
    public void addTokens(Integer count) {
    }
}
