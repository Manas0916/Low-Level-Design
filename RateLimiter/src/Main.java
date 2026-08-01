

public class Main {
    public static void main(String[] args) {
        Manager rateLimitManager = new Manager(RateLimiterType.LEAKY_BUCKET);
        rateLimitManager.run();
    }
}
