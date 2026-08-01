package src;

public class Main {
    public static void main(String[] args) {
        RateLimiterManager rateLimitManager = new RateLimiterManager(RateLimiterType.LEAKY_BUCKET);
        rateLimitManager.run();
    }
}
