package RateLimiter;

public interface RefillableRateLimiter extends RateLimiter {
    void addTokens(int count);
}
