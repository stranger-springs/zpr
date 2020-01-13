package com.strangersprings.zpr.client.api.currency;

import com.strangersprings.zpr.client.model.Currency;

import java.util.List;

import static java.util.stream.Collectors.toList;

public interface UICurrencyMapper {
    UICurrencyDTO toUICurrencyDTO(Currency currency);

    default List<UICurrencyDTO> toUICurrencyDTOs(List<Currency> currencyList) {
        return currencyList.stream()
                .map(this::toUICurrencyDTO)
                .collect(toList());
    }
}
