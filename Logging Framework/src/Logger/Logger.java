package Logger;

import Appender.Appender;
import Config.LoggerConfiguration;
import Enums.LogLevel;
import Model.LogRecord;

import java.util.List;

public class Logger {
    private final LoggerConfiguration configuration;
    private final String loggerName;

    public Logger(String loggerName, LoggerConfiguration configuration) {
        this.loggerName = loggerName;
        this.configuration = configuration;
    }

    private void log(LogLevel level, String message) {
        LogLevel configuredLevel = configuration.getLogLevel();
        if(level.ordinal() < configuredLevel.ordinal()) {
            return;
        }
        LogRecord logRecord = new LogRecord(level, message, this.loggerName);
        List<Appender> appenders = this.configuration.getAppenders();
        for(Appender appender: appenders) {
            appender.append(logRecord);
        }
    }

    public void info(String msg) {
        this.log(LogLevel.INFO, msg);
    }
    public void debug(String msg) {
        this.log(LogLevel.DEBUG, msg);
    }
    public void warning(String msg) {
        this.log(LogLevel.WARN, msg);
    }
    public void error(String msg) {
        this.log(LogLevel.ERROR, msg);
    }
    public void fatal(String msg) {
        this.log(LogLevel.FATAL, msg);
    }
    public void trace(String msg) {
        this.log(LogLevel.TRACE, msg);
    }

}
