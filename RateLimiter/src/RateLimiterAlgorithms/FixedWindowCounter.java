package src.RateLimiterAlgorithms;

import src.RateLimiter;
import src.Request.Request;

import java.time.Instant;

public class FixedWindowCounter implements RateLimiter {
    private int requestCounter ;
    private int maxRequestsThreshold;
    private int windowSize;
    private long windowStart;

    public FixedWindowCounter(int windowSize, int maxRequestsThreshold) {
        this.windowSize = windowSize;
        this.requestCounter = 0;
        this.maxRequestsThreshold = maxRequestsThreshold;
        this.windowStart = Instant.now().getEpochSecond();
    }

    @Override
    public synchronized boolean allowRequest(Request request) {
        long now = Instant.now().getEpochSecond();

        if (now - this.windowStart >= this.windowSize) {
            System.out.println("Window Expired!. Resetting");
            this.windowStart = now;
            this.requestCounter = 0;
        }

        if(this.requestCounter < this.maxRequestsThreshold) {
            this.requestCounter ++;
            return true;
        }

        return false;
    }
}
