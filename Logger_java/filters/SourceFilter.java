package filters;

import java.util.List;

import core.LogFilter;
import core.LogLevel;
import core.LogMessage;

public class SourceFilter implements LogFilter {

    private String sourcePattern;
    private List<String> patterns;
    private boolean include;
    private LogLevel level;

    public SourceFilter(List<String> patterns) {
        this.patterns = patterns;
        this.include = true;
        this.level = LogLevel.DEBUG;
    }

    @Override
    public boolean shouldLog(LogMessage message) {
        if (!message.getLevel().isGreaterOrEqual(level))
            return false;

        String msg = message.getMessage();
        for (String p : patterns) {
            if (msg.contains(p))
                return true; // OR logic
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
