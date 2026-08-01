

public interface RefillableRateLimiter extends RateLimiter {
    void addTokens(Integer count);
}
