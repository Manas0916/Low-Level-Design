package Config;

import Appender.Appender;
import Enums.LogLevel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LoggerConfiguration {
    private LogLevel logLevel;
    private List<Appender> appenders;

    public LoggerConfiguration(LogLevel logLevel){
        this.logLevel = logLevel;
        this.appenders = new ArrayList<>();
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public LogLevel getLogLevel() {
        return this.logLevel;
    }

    public void addAppender(Appender appender) {
        appenders.add(appender);
    }

    public void removeAppender(Appender appender) {
        this.appenders.remove(appender);
    }

    public List<Appender> getAppenders() {
        return Collections.unmodifiableList(appenders);
    }
}
