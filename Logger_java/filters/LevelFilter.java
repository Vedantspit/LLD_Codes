package filters;

import core.LogFilter;
import core.LogLevel;
import core.LogMessage;

public class LevelFilter implements LogFilter {

    private LogLevel level;

    public LevelFilter() {
        this(LogLevel.DEBUG);
    }

    public LevelFilter(LogLevel level) {
        this.level = level;
    }

    @Override
    public boolean shouldLog(LogMessage message) {
        return message.getLevel().isGreaterOrEqual(level);
    }

    @Override
    public void setLevel(LogLevel level) {
        this.level = level;

    }

    @Override
    public LogLevel getLevel() {
        return level;
    }
}
