import { LogLevel, LogLevelName } from "./LogLevels.js";

class Logger {
  constructor() {
    if (Logger.instance) return Logger.instance;

    this.configLevel = LogLevel.INFO;
    this.appenders = [];
    Logger.instance = this;
  }

  addAppender(appender) {
    this.appenders.push(appender);
  }
  setLogLevel(level) {
    this.configLevel = level;
  }

  log(level, msg) {
    if (level >= this.configLevel) {
      const timestamp = new Date().toISOString();
      const levelName = LogLevelName[level];
      const formattedMessage = `[${timestamp}] [${levelName}]: ${msg}`;

      this.appenders.forEach((appender) => appender.send(formattedMessage));
    }
  }
  debug(msg) {
    this.log(LogLevel.DEBUG, msg);
  }
  info(msg) {
    this.log(LogLevel.INFO, msg);
  }
  warn(msg) {
    this.log(LogLevel.WARN, msg);
  }
  error(msg) {
    this.log(LogLevel.ERROR, msg);
  }
  fatal(msg) {
    this.log(LogLevel.FATAL, msg);
  }
}

const loggerInstance = new Logger();
// Object.freeze(loggerInstance);

export default loggerInstance;
