package com.epam.streamdp.ten.framework.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class Log {
    public static final Logger LOGGER = LogManager.getLogger("yandexLogger");

    private Log() {
        throw new IllegalStateException("Logger class");
    }

    public static void debug(String message) {
        LOGGER.debug(message);
    }

    public static void info(String message) {
        LOGGER.info(message);
    }

    public static void screenshot(File screenshot) {
        LOGGER.info("Screenshot saved : " + "<a href=\"file://" + screenshot.getAbsolutePath() + "\" target=\"blank\">"
                + screenshot.getName() + "</a>");
    }

    public static void error(String message) {
        LOGGER.error(message);
    }
}