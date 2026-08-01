package src.Request;

public class Request {
    int requestId;
    String clientName;
    public Request(int requestId, String clientName) {
        this.requestId = requestId;
        this.clientName = clientName;
    }

    @Override
    public String toString() {
        return clientName + "-" + requestId;
    }
}
