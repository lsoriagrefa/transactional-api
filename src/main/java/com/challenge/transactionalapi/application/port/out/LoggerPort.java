package com.challenge.transactionalapi.application.port.out;

public interface LoggerPort {
	
    void info(String message);

    void error(String message, Throwable error);
}
