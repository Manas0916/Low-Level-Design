package RateLimiter;

import java.util.concurrent.TimeUnit;
public class RefillService {
    RefillableRateLimiter bucket;
    int refillTokens;
    int refillInterval;

    RefillService(RefillableRateLimiter bucket, int refillTokens, int refillInterval) {
        this.bucket = bucket;
        this.refillTokens = refillTokens;
        this.refillInterval = refillInterval;
    }

    public void startRefill() {
        Thread daemonThread;
        try {
            daemonThread = new Thread(this::run);
            daemonThread.setDaemon(true);
            daemonThread.start();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void run() {
        try {
            while(true) {
                TimeUnit.SECONDS.sleep(this.refillInterval);
                this.bucket.addTokens((this.refillTokens));
                System.out.println("Refilled -> " + this.refillTokens + "Tokens");
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            Thread.currentThread().interrupt();
        }

    }


}
