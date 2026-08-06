package Model;

import Enums.LogLevel;

import java.time.LocalDateTime;

public class LogRecord {
    private final LocalDateTime timestamp;
    private final LogLevel logLevel;
    private final String message;
    private final String loggerName;
    private final String threadName;

    public LogRecord(LogLevel logLevel, String message, String loggerName) {
        this.timestamp = LocalDateTime.now();;
        this.logLevel = logLevel;
        this.message = message;
        this.loggerName = loggerName;
        this.threadName = Thread.currentThread().getName();
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public String getMessage() {
        return message;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public String getThreadName() {
        return threadName;
    }

    @Override
    public String toString() {
        return "LogRecord{" +
                "timestamp=" + timestamp +
                ", level=" + logLevel +
                ", loggerName='" + loggerName + '\'' +
                ", threadName='" + threadName + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
