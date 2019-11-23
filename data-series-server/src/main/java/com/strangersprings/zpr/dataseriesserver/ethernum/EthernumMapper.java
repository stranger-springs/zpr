package com.strangersprings.zpr.dataseriesserver.ethernum;

import org.springframework.stereotype.Component;

@Component
public class EthernumMapper {

    public EthernumDTO toEthernumDTO(Ethernum ethernum) {
        if (ethernum == null) {
            return null;
        }

        return EthernumDTO.builder()
                .id(ethernum.getId())
                .timestamp(ethernum.getTimestamp())
                .price(ethernum.getPrice())
                .build();
    }

}
