package src.Factory;

import src.RateLimiter;
import src.RateLimiterType;
import src.RateLimiterAlgorithms.LeakyBucket;
import src.RateLimiterAlgorithms.Tokenbucket;
import src.RateLimiterAlgorithms.FixedWindowCounter;

public class RateLimiterFactory {
    public RateLimiter create(RateLimiterType type) {

        return switch (type) {
            case TOKEN_BUCKET -> new Tokenbucket(10);
            case LEAKY_BUCKET -> new LeakyBucket(4, 10);
            case FIXED_WINDOW -> new FixedWindowCounter(3, 6);
            case SLIDING_WINDOW ->
                // return new SlidingWindow(...); To be implemented
                    throw new IllegalArgumentException(
                            "Unsupported Algorithm : " + type);
            default -> throw new IllegalArgumentException(
                    "Unsupported Algorithm : " + type);
        };
    }
}
