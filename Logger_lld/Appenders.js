import { log } from "console";
import fs from "fs";

class LogAppender {
  send(msg) {
    throw new Error("Method 'send()' must be implemented");
  }
}
class ConsoleAppender extends LogAppender {
  send(msg) {
    console.log(msg);
  }
}

class FileAppender extends LogAppender {
  constructor(filePath) {
    super();
    this.filePath = filePath;
  }

  send(msg) {
    try {
      fs.appendFileSync(this.filePath, msg + "\n", "utf-8");
    } catch (err) {
      console.error("Failed to write to log file:", err);
    }
  }
}

export { ConsoleAppender, FileAppender };
