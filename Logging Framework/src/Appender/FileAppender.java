package Appender;

import Formatter.Formatter;
import Model.LogRecord;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileAppender extends Appender {
    private final BufferedWriter writer;
    public FileAppender(String filePath, Formatter formatter) throws IOException {
        super(formatter);
        this.writer = new BufferedWriter(new FileWriter(filePath, true));
    }

    @Override
    public synchronized void append(LogRecord logRecord) {
        try {
            this.writer.write(this.formatter.format(logRecord));
            this.writer.newLine();
            this.writer.flush();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
