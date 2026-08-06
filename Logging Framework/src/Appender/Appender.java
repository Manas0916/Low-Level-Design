package Appender;

import Formatter.Formatter;
import Model.LogRecord;

public abstract class Appender {
    protected Formatter formatter;

    public Appender(Formatter formatter) {
        this.formatter = formatter;
    }
    public abstract void append(LogRecord logRecord);
}
