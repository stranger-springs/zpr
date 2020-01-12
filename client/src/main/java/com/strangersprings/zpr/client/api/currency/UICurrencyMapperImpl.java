package com.strangersprings.zpr.client.api.currency;

import com.strangersprings.zpr.client.dto.currency.CurrencyDTO;
import com.strangersprings.zpr.client.model.Currency;
import org.springframework.stereotype.Component;

@Component
public class UICurrencyMapperImpl implements UICurrencyMapper {

    @Override
    public UICurrencyDTO toUICurrencyDTO(Currency currency) {
        return new UICurrencyDTO(currency.getId(), currency.getTimestamp(),
                currency.getPrice().doubleValue());
    }
}
