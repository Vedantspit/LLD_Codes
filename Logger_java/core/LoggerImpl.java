package core;

import formatters.SimpleFormatter;
import appenders.ConsoleAppender;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class LoggerImpl implements Logger {

    private final String name;
    private LogLevel level;
    private final List<LogAppender> appenders;
    private final List<LogFilter> filters;

    public LoggerImpl() {
        this("DefaultLogger");
    }

    public LoggerImpl(String name) {
        this(name, true);
    }

    public LoggerImpl(String name, boolean addDefaultAppender) {
        this.name = name;
        this.level = LogLevel.DEBUG;
        this.appenders = Collections.synchronizedList(new ArrayList<>());
        this.filters = Collections.synchronizedList(new ArrayList<>());
        if (addDefaultAppender) {
            addAppender(new ConsoleAppender());
        }
    }

    public LoggerImpl(String name, LogConfiguration config) {
        this(name);
        this.level = config.getRootLevel();
    }

    @Override
    public synchronized void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    @Override
    public synchronized void info(String message) {
        log(LogLevel.INFO, message);
    }

    @Override
    public synchronized void warning(String message) {
        log(LogLevel.WARNING, message);
    }

    @Override
    public synchronized void error(String message) {
        log(LogLevel.ERROR, message);
    }

    @Override
    public synchronized void fatal(String message) {
        log(LogLevel.FATAL, message);
    }

    @Override
    public synchronized void log(LogLevel level, String messsage) {
        if (!level.isGreaterOrEqual(this.level)) {
            return;
        }
        LogMessage logMessage = new LogMessage.Builder()
                .level(level).message(messsage)
                .source(getCallingClass()).build();

        for (LogFilter filter : filters) {
            if (!filter.shouldLog(logMessage))
                return;
        }
        for (LogAppender appender : appenders) {
            if (appender.isEnabled(level)) {
                appender.append(logMessage);
            }
        }
    }

    @Override
    public void setLevel(LogLevel level) {
        this.level = level;
    }

    @Override
    public void addAppender(LogAppender appender) {
        appenders.add(appender);
    }

    @Override
    public void addFilter(LogFilter filter) {
        filters.add(filter);
    }

    @Override
    public void removeFilter(LogFilter filter) {
        filters.remove(filter);
    }

    @Override
    public List<LogAppender> getAppenders() {
        return new ArrayList<>(appenders);
    }

    @Override
    public List<LogFilter> getFilters() {
        return new ArrayList<>(filters);

    }

    private String getCallingClass() {
        try {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace.length > 3) {
                String className = stackTrace[3].getClassName();
                String methodName = stackTrace[3].getMethodName();
                return className + "." + methodName;
            }
        } catch (Exception e) {

        }
        return "Unknown";
    }

    public String getName() {
        return name;
    }

    public LogLevel getLevel() {
        return level;
    }
}
