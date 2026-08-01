
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LeakService {

    private final LeakyBucket bucket;

    private final int leakRate;

    private final ScheduledExecutorService scheduler =
            Executors.newSingleThreadScheduledExecutor();

    public LeakService(LeakyBucket bucket, int leakRate) {
        this.bucket = bucket;
        this.leakRate = leakRate;
    }

    public void start() {

        scheduler.scheduleAtFixedRate(
                () -> this.bucket.leak(leakRate), 1, 1, TimeUnit.SECONDS
        );
    }
}