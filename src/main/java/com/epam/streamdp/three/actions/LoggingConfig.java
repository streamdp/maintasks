package com.epam.streamdp.three.actions;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.*;

public class LoggingConfig {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(LoggingConfig.class.getName());

    private LoggingConfig() { throw new IllegalStateException("Utility class"); }

    public static void loadAndApplyLoggingConfig(){
        try {
            final LogManager logManager = LogManager.getLogManager();
            URL configURL = LoggingConfig.class.getResource(File.separator + "logging.properties");
            if (configURL != null) {
                try (InputStream is = configURL.openStream()) {
                    logManager.readConfiguration(is);
                }
            } else {
                // Programmatic configuration
                System.setProperty("java.util.logging.SimpleFormatter.format",
                        "%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS.%1$tL %4$-7s [%3$s] %5$s %6$s%n");

                final ConsoleHandler consoleHandler = new ConsoleHandler();
                consoleHandler.setLevel(Level.FINEST);
                consoleHandler.setFormatter(new SimpleFormatter());

                final Logger app = Logger.getLogger("app");
                app.setLevel(Level.FINEST);
                app.addHandler(consoleHandler);
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception: ", ex);
        }
    }
}
