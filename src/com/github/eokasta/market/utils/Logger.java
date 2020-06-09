package com.github.eokasta.market.utils;

public class Logger {

    private static final String SYSTEM_LOGGER = "[Sistema] %s";

    public void log(String message) {
        System.out.println(String.format(SYSTEM_LOGGER, message));
    }

    public void log(String message, String... args) {
        log(String.format(message, args));
    }

}
