package main;

import core.*;

import java.util.List;

import appenders.*;
import filters.*;
import formatters.*;

public class LoggingDemo {

    public static void main(String[] args) {
        System.out.println("<== -- LoggingFramework Demo 🪵==>");

        demoBasicLogging();
        demoMultipleAppenders();
        demoCustomFormatters();
        demoFilters();
        demoThreadSafety();

        System.out.println("\n=== Demo Complete ===");
    }

    private static void demoBasicLogging() {
        System.out.println("1. Basic Logging Demo:");
        System.out.println("----------------------");

        Logger logger = new LoggerImpl("BasicLogger");

        logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warning("This is a warning message");
        logger.error("This is an error message");
        logger.fatal("This is a fatal message");
        System.out.println();

    }

    private static void demoMultipleAppenders() {
        System.out.println("2. Multiple Appenders Demo:");
        System.out.println("---------------------------");

        Logger logger = new LoggerImpl("MultiAppenderLogger");

        // Add file appender
        FileAppender fileAppender = new FileAppender("demo.log");
        logger.addAppender(fileAppender);

        logger.info("This message goes to both console and file");
        logger.error("This error also goes to both destinations");

        System.out.println("Check 'demo.log' file for the logged messages");
        System.out.println();
    }

    private static void demoCustomFormatters() {
        System.out.println("3. Custom Formatters Demo:");
        System.out.println("--------------------------");
        Logger logger = new LoggerImpl("FormatterLogger");

        SimpleFormatter customFormatter = new SimpleFormatter();
        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setFormatter(customFormatter);
        logger.addAppender(consoleAppender);
        logger.info("This message uses custom formatting");
        logger.error("This error also uses custom formatting");

        System.out.println();
    }

    private static void demoFilters() {
        System.out.println("4. Filters Demo:");
        System.out.println("----------------");
        Logger logger = new LoggerImpl("FilterLogger");
        LevelFilter levelFilter = new LevelFilter(LogLevel.WARNING);
        logger.addFilter(levelFilter);

        logger.addFilter(new SourceFilter(List.of("db", "payment")));

        logger.debug("DB Error This debug message will be filtered out");
        logger.info("This info message will be filtered out");
        logger.warning("db ERROR This warning message will be shown");
        logger.warning("payment ERROR This warning message will be shown");
        logger.error("Auth This error message will be shown");

        System.out.println();

    }

    private static void demoThreadSafety() {

        System.out.println("5. Thread Safety Demo:");
        System.out.println("----------------------");
        Logger logger = new LoggerImpl("ThreadSafeLogger");

        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            final int threadId = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 3; j++) {
                    logger.info("Thread " + threadId + " - Message " + j);
                    try {
                        Thread.sleep(10); // Small delay to increase concurrency
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("All threads completed - check for any mixed-up messages above");
        System.out.println();
    }
}
