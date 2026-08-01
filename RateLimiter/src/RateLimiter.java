package src;

import src.Request.Request;

public interface RateLimiter {
    boolean allowRequest(Request request);
}
