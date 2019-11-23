package com.strangersprings.zpr.client.service;

import com.strangersprings.zpr.client.model.*;
import com.strangersprings.zpr.client.service.calc.CurrencyDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class CryptocurrencyMapper {

    Bitcoin toBitcoin(CurrencyData data, LocalDateTime timestamp) {
        return Bitcoin.builder()
                .id(-1L)
                .price(BigDecimal.valueOf(data.getPrice()))
                .timestamp(timestamp)
                .build();
    }

    Ethernum toEthernum(CurrencyData data, LocalDateTime timestamp) {
        return Ethernum.builder()
                .id(-1L)
                .price(BigDecimal.valueOf(data.getPrice()))
                .timestamp(timestamp)
                .build();
    }

    Litecoin toLiteCoin(CurrencyData data, LocalDateTime timestamp) {
        return Litecoin.builder()
                .id(-1L)
                .price(BigDecimal.valueOf(data.getPrice()))
                .timestamp(timestamp)
                .build();
    }

    ZCash toZcash(CurrencyData data, LocalDateTime timestamp) {
        return ZCash.builder()
                .id(-1L)
                .price(BigDecimal.valueOf(data.getPrice()))
                .timestamp(timestamp)
                .build();
    }

    CurrencyDTO toCurrencyDTO(CurrencyData data) {
        return new CurrencyDTO(ThreadLocalRandom.current().nextLong(Long.MAX_VALUE), data.getPrice());
    }
}
