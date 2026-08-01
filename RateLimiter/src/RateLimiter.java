

public interface RateLimiter {
    abstract boolean allowRequest(Request request);
}

