package src.RateLimiterAlgorithms;

import src.RefillableRateLimiter;
import src.Request.Request;

public class Tokenbucket implements RefillableRateLimiter {
    int capacity, tokensCount;
    final Object lock;
    public Tokenbucket(int cap) {
        this.capacity = cap;
        this.tokensCount = cap;
        lock = new Object();
    }

    public void addTokens(Integer count) {
        synchronized (lock) {
            this.tokensCount = Math.min(this.capacity, this.tokensCount + count);
        }
    }

    public int getTokens() {
        synchronized (lock) {
            return this.tokensCount;
        }
    }

    @Override
    public boolean allowRequest(Request request) {
        synchronized (lock) {
            if (this.tokensCount == 0) {
                return false;
            }
            this.tokensCount -= 1;
            return true;
        }
    }
}
