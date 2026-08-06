import Appender.*;
import Config.LoggerConfiguration;
import Enums.LogLevel;
import Formatter.JsonFormatter;
import Formatter.SimpleFormatter;
import Logger.LogManager;
import Logger.Logger;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        LoggerConfiguration configuration =
                new LoggerConfiguration(LogLevel.INFO);

        configuration.addAppender(new ConsoleAppender(new SimpleFormatter()));

        configuration.addAppender(new FileAppender("logs.txt", new JsonFormatter()));

        LogManager manager = LogManager.getInstance(configuration);

        Logger logger = manager.getLogger(Main.class.getSimpleName());

        logger.trace("Trace");

        logger.debug("Debug");

        logger.info("Application Started");

        logger.warning("Warning");

        logger.error("Something went wrong");

        logger.fatal("Fatal Error");
    }
}