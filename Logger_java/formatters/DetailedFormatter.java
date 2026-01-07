package formatters;

import core.LogFormatter;
import core.LogMessage;

import java.time.format.DateTimeFormatter;

public class DetailedFormatter implements LogFormatter {
    private String pattern;
    private String dateFormat;
    private DateTimeFormatter dateTimeFormatter;

    public DetailedFormatter() {
        this("[%LEVEL] %TIMESTAMP [%SOURCE] - %MESSAGE");
    }

    public DetailedFormatter(String pattern) {
        this.pattern = pattern;
        this.dateFormat = "yyyy-MM-dd HH:mm:ss";
        this.dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat);
    }

    @Override
    public String format(LogMessage message) {
        String formatted = pattern.replace("%LEVEL", message.getLevel().toString())
                .replace("%TIMESTAMP", dateTimeFormatter.format(message.getTimestamp()))
                .replace("%MESSAGE", message.getMessage())
                .replace("%SOURCE", message.getSource());
        return formatted;
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