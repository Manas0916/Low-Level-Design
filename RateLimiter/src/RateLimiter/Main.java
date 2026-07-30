package RateLimiter;

public class Main {
    public static void main(String[] args) {
        Manager rateLimitManager = new Manager(RateLimiterType.TOKEN_BUCKET);
        rateLimitManager.run();
    }
}
