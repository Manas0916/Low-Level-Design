package Logger;

import Config.LoggerConfiguration;

import java.util.concurrent.ConcurrentHashMap;

public class LogManager {

    private final ConcurrentHashMap<String, Logger> loggers;
    private final LoggerConfiguration configuration;
    private static LogManager instance;


    private LogManager( LoggerConfiguration configuration){
        this.loggers = new ConcurrentHashMap<>();
        this.configuration = configuration;
    }

    public Logger getLogger(String loggerName){
        return loggers.computeIfAbsent(
                loggerName,
                name -> new Logger(name, configuration)
        );
    }

    public static synchronized LogManager getInstance(LoggerConfiguration configuration) {
        if(instance == null) {
            instance = new LogManager(configuration);
        }
        else if(configuration != instance.configuration) {
            throw new IllegalStateException(
                    "LogManager already initialized"
            );
        }
        return instance;
    }
}
