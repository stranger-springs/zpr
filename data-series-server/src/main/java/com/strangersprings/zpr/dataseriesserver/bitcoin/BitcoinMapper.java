package com.strangersprings.zpr.dataseriesserver.bitcoin;

import org.springframework.stereotype.Component;

@Component
public class BitcoinMapper {

    public BitcoinDTO toBitcoinDTO(Bitcoin bitcoin) {
        if (bitcoin == null) {
            return null;
        }

        return BitcoinDTO.builder()
                .id(bitcoin.getId())
                .timestamp(bitcoin.getTimestamp())
                .price(bitcoin.getPrice())
                .build();
    }

}
