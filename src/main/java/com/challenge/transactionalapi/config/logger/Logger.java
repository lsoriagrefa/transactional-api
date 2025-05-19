package com.challenge.transactionalapi.config.logger;

import com.challenge.transactionalapi.application.port.out.LoggerPort;
import org.apache.logging.log4j.LogManager;

public class Logger implements LoggerPort{
	
    private org.apache.logging.log4j.Logger logger;

    public void setLogger(Class<?> clazz) {
        this.logger = LogManager.getLogger(clazz);
    }

    @Override
    public void info(String message) {
        logger.info(message);
    }

    @Override
    public void error(String message, Throwable error) {
        logger.error(message, error);
    }
}