package filters;

import core.LogFilter;
import core.LogLevel;
import core.LogMessage;

import java.util.List;

public class SourceFilter implements LogFilter {

    private String sourcePattern;
    private boolean include;
    private LogLevel level;
    private final List<String> patterns;

    public SourceFilter(List<String> patterns) {
        this.patterns = patterns;
        this.include = true;
        this.level = LogLevel.DEBUG;

    }

    // public SourceFilter(String sourcePattern, boolean include) {
    // this.sourcePattern = sourcePattern;
    // this.include = include;
    // this.level = LogLevel.DEBUG;
    // }

    @Override
    public boolean shouldLog(LogMessage message) {
        if (!message.getLevel().isGreaterOrEqual(level)) {
            return false;
        }
        if (message.getSource() == null) {
            return !include;
        }
        String msg = message.getMessage().toLowerCase();
        for (String pattern : patterns) {
            if (msg.contains(pattern.toLowerCase()))
                return true;
        }
        return false;
    }

    @Override
    public void setLevel(LogLevel level) {
        this.level = level;
    }

    @Override
    public LogLevel getLevel() {
        return level;
    }

    public String getSourcePattern() {
        return sourcePattern;
    }

    public void setSourcePattern(String sourcePattern) {
        this.sourcePattern = sourcePattern;
    }

    public boolean isInclude() {
        return include;
    }

    public void setInclude(boolean include) {
        this.include = include;
    }

}
