package com.strangersprings.zpr.client.service;

import com.strangersprings.zpr.client.model.CurrencyData;
import com.strangersprings.zpr.client.model.Bitcoin;
import com.strangersprings.zpr.client.model.Ethernum;
import com.strangersprings.zpr.client.model.Litecoin;
import com.strangersprings.zpr.client.model.ZCash;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
}
