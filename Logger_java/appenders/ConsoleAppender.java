package appenders;

import java.io.PrintStream;

import core.LogAppender;
import core.LogFormatter;
import core.LogLevel;
import core.LogMessage;
import formatters.SimpleFormatter;

public class ConsoleAppender implements LogAppender {

    private LogLevel level;
    private LogFormatter formatter;
    private PrintStream outputStream;

    public ConsoleAppender() {
        this(LogLevel.DEBUG);
    }

    public ConsoleAppender(LogLevel level) {
        this.level = level;
        this.formatter = new SimpleFormatter();
        this.outputStream = System.out;
    }

    @Override
    public void append(LogMessage message) {
        if (!isEnabled(level)) {
            return;
        }
        String formattedMessage = formatter.format(message);
        if (message.getLevel() == LogLevel.ERROR || message.getLevel() == LogLevel.FATAL) {
            System.err.println(formattedMessage);
        } else {
            outputStream.println(formattedMessage);
        }
    }

    @Override
    public void setLevel(LogLevel level) {
        this.level = level;
    }

    @Override
    public LogLevel getLevel() {
        return level;
    }

    @Override
    public boolean isEnabled(LogLevel level) {
        return level.isGreaterOrEqual(this.level);
    }

    @Override
    public void setFormatter(LogFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public LogFormatter getFormatter() {
        return formatter;
    }

    public void setOutputStream(PrintStream outputStream) {
        this.outputStream = outputStream;
    }
}
