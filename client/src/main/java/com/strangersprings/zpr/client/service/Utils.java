package com.strangersprings.zpr.client.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Utils {
    public static LocalDateTime getCurrentTimestamp() {
        return LocalDateTime.now();
    }

    public static LocalDateTime timestampFromSeconds(long val) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(val), ZoneId.of("Europe/Warsaw"));
    }
}
