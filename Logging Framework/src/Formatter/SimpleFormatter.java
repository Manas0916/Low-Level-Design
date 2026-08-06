package Formatter;

import Model.LogRecord;

public class SimpleFormatter implements Formatter{

    @Override
    public String format(LogRecord logRecord) {
        return " " +
                logRecord.getTimestamp() +
                "[" +
                logRecord.getLogLevel() +
                "] " +
                "[" +
                logRecord.getLoggerName() +
                "] " +
                "[" +
                logRecord.getThreadName() +
                "] " +
                logRecord.getMessage();
    }
}
