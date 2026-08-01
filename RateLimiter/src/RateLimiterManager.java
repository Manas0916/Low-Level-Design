package src;

import src.Client.Client;
import src.Factory.RateLimiterFactory;
import src.RateLimiterAlgorithms.LeakyBucket;
import src.RateLimiterAlgorithms.Tokenbucket;
import src.services.LeakService;
import src.services.RefillService;

import java.util.ArrayList;

public class RateLimiterManager {
    private final RateLimiterType algorithm;
    private final  RateLimiter rateLimiter;

    public RateLimiterManager(RateLimiterType algorithm)
    {
        RateLimiterFactory rateLimiterFactory = new RateLimiterFactory();
        this.algorithm = algorithm;
        this.rateLimiter = rateLimiterFactory.create(algorithm);
    }

    public void run()  {
        try {
            if (this.rateLimiter == null) {
                System.out.println("Cannot start - no rate limiter available");
                return;
            }
            else if (this.rateLimiter instanceof Tokenbucket TokenBucket) {
                RefillService refillService = new RefillService(TokenBucket, 3, 1);
                refillService.startRefill();
                System.out.println("Refill service started for " + algorithm);
            }
            else if(this.rateLimiter instanceof LeakyBucket lb) {
                LeakService leak = new LeakService(lb, 2);
                leak.start();
            }
            else {
                System.out.println("No refill service needed for " + algorithm);
            }

            ArrayList<Thread> clients = new ArrayList<>();
            for(int i = 0; i < 5; i ++) {
                Client client = new Client(this.rateLimiter, "client -" + i);
                Thread clientThread = new Thread(client, "client-" + i);
                clientThread.setDaemon(true);
                clientThread.start();
                clients.add(clientThread);
            }

            for(Thread client: clients) {
                client.join();
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
