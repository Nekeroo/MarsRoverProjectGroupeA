package org.marsrover.console;

import java.util.logging.Logger;

public class LoggerConsole implements ILogger {

    private static final Logger LOGGER = Logger.getLogger(LoggerConsole.class.getName());

    @Override
    public void log(String message) {
        LOGGER.info(message);
    }
}
