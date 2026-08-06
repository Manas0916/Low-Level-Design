package Appender;

import Formatter.Formatter;
import Model.LogRecord;

public class ConsoleAppender extends Appender {
    public ConsoleAppender(Formatter formatter) {
        super(formatter);
    }

    @Override
    public void append(LogRecord logRecord) {
        System.out.println(this.formatter.format(logRecord));
    }
}
