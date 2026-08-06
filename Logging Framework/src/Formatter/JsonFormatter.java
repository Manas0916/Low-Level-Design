package Formatter;

import Model.LogRecord;

public class JsonFormatter implements Formatter {
    @Override
    public String format(LogRecord logRecord) {
        return "{" +
                "timestamp: " + logRecord.getTimestamp() + "," +
                "level: " + logRecord.getLogLevel() + "," +
                "logger: " + logRecord.getLoggerName() + "," +
                "thread: " + logRecord.getThreadName() + "," +
                "message: " + logRecord.getMessage() + "," +
                "}";
    }
}
