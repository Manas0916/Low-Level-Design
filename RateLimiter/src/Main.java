package src;

public class Main {
    public static void main(String[] args) {
        RateLimiterManager rateLimitManager = new RateLimiterManager(RateLimiterType.FIXED_WINDOW);
        rateLimitManager.run();
    }
}
