

public class RateLimiterFactory {
    public RateLimiter create(RateLimiterType type) {

        return switch (type) {
            case TOKEN_BUCKET -> new Tokenbucket(10);
            case LEAKY_BUCKET -> new LeakyBucket(4, 10);
            case FIXED_WINDOW ->
                // return new FixedWindow(...); To be implemented
                    throw new IllegalArgumentException(
                            "Unsupported Algorithm : " + type);
            case SLIDING_WINDOW ->
                // return new SlidingWindow(...); To be implemented
                    throw new IllegalArgumentException(
                            "Unsupported Algorithm : " + type);
            default -> throw new IllegalArgumentException(
                    "Unsupported Algorithm : " + type);
        };
    }
}
