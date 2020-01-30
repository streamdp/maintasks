package com.epam.streamdp.lerning.collection;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerExample {
    public static void main(String[] args)
    {
        // Create a Logger
        Logger logger = Logger.getLogger(LoggerExample.class.getName());
        // log messages using log(Level level, String msg)
        logger.log(Level.INFO, "This is message 1");
        logger.log(Level.WARNING, "This is message 2");
    }
}