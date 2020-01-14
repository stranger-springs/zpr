package com.strangersprings.zpr.client.process;

import java.math.BigDecimal;
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

    public static BigDecimal getRoundedPrice(double val) {
        return BigDecimal.valueOf(val).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal getRoundedValue(double val) {
        return BigDecimal.valueOf(val).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
