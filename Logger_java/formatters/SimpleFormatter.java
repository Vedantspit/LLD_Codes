package formatters;

import core.LogFormatter;
import core.LogMessage;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class SimpleFormatter implements LogFormatter {
    private String pattern;
    private String dateFormat;
    private DateTimeFormatter dateTimeFormatter;

    public SimpleFormatter() {
        this("[%LEVEL] %TIMESTAMP - %MESSAGE");
    }

    public SimpleFormatter(String pattern) {
        this.pattern = pattern;
        this.dateFormat = "yyyy-MM-dd HH:mm:ss";
        this.dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat);
    }

    @Override
    public String format(LogMessage message) {
        ZonedDateTime dateTime = message.getTimestamp().atZone(ZoneId.systemDefault());
        return pattern.replace("%LEVEL", message.getLevel().toString())
                .replace("%TIMESTAMP", dateTimeFormatter.format(dateTime))
                .replace("%MESSAGE", message.getMessage());
    }

    @Override
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String getPattern() {
        return pattern;
    }

    @Override
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
        this.dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat);
    }
}