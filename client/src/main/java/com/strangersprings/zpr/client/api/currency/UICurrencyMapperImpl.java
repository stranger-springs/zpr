package com.strangersprings.zpr.client.api.currency;

import com.strangersprings.zpr.client.model.Currency;
import org.springframework.stereotype.Component;

@Component
public class UICurrencyMapperImpl implements UICurrencyMapper {

    @Override
    public UICurrencyDTO toUICurrencyDTO(Currency currency) {
        return UICurrencyDTO.builder()
                .id(currency.getId())
                .price(currency.getPrice().doubleValue())
                .timestamp(currency.getTimestamp())
                .type(currency.getType().getName())
                .build();
    }

}
