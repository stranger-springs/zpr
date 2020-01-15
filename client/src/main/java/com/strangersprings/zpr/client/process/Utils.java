package com.strangersprings.zpr.client.process;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    /**
     * @param val wartosc do zaokraglenia
     * @return zaokraglona wartosc
     */

    public static BigDecimal getRoundedPrice(double val) {
        return BigDecimal.valueOf(val).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * @param val wartosc do zaokraglenia
     * @return zaokraglona wartosc
     */
    public static BigDecimal getRoundedValue(double val) {
        return BigDecimal.valueOf(val).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public static String loadConfigFile(Class t,String filename) {
        String config = "";
        try {
            InputStream inputStream = t.getClassLoader().getResourceAsStream(filename);
            config = CharStreams.toString(new InputStreamReader(
                    inputStream, Charsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }
}
