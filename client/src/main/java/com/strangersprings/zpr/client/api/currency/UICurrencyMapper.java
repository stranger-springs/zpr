package com.strangersprings.zpr.client.api.currency;

import com.strangersprings.zpr.client.model.Currency;

public interface UICurrencyMapper {

    UICurrencyDTO toUICurrencyDTO(Currency currency);
}
