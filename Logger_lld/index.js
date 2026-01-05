import logger from "./Logger.js";
import { ConsoleAppender, FileAppender } from "./Appenders.js";
import { LogLevel } from "./LogLevels.js";
const consoleSink = new ConsoleAppender();
const fileSink = new FileAppender("./app.log");

logger.addAppender(consoleSink);
logger.addAppender(fileSink);
logger.setLogLevel(LogLevel.DEBUG);

function UserModule() {
  logger.debug(" DEBUG : User module");
  logger.info("User 'Alice' clicked the login button.");
}
function PaymentModule() {
  logger.debug("DEBUG :  Payment");
  logger.error("Error in stripe payment gateway");
}

UserModule();
PaymentModule();

console.log("Check 'app.log' to see the saved logs!");
