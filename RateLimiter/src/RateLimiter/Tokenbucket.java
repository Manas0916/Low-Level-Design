package RateLimiter;

public class Tokenbucket implements RefillableRateLimiter {
    int capacity, tokensCount;
    final Object lock;
    Tokenbucket(int cap) {
        this.capacity = cap;
        this.tokensCount = cap;
        lock = new Object();
    }

    public void addTokens(int count) {
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
    public boolean allowRequest() {
        synchronized (lock) {
            if (this.tokensCount == 0) {
                return false;
            }
            this.tokensCount -= 1;
            return true;
        }
    }
}
