package RateLimiter;

import java.util.ArrayList;

public class Manager {
    private final RateLimiterType algorithm;
    private final  RateLimiter rateLimiter;

    public Manager(RateLimiterType algorithm)
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
            if (this.rateLimiter instanceof RefillableRateLimiter refillableBucket) {
                RefillService refillService = new RefillService(refillableBucket, 3, 1);
                refillService.startRefill();
                System.out.println("Refill service started for " + algorithm);
            } else {
                System.out.println("No refill service needed for " + algorithm);
            }

            ArrayList<Client> clients = new ArrayList<Client>();
            for(int i = 0; i < 5; i ++) {
                Client client = new Client(this.rateLimiter, "client -" + i);
                client.start();
                clients.add(client);
            }

            for(Client client: clients) {
                client.join();
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
