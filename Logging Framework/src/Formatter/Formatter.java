package Formatter;

import Model.LogRecord;

public interface Formatter {

    String format(LogRecord logRecord);
}
