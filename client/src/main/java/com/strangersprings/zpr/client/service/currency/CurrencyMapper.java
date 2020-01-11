package com.strangersprings.zpr.client.service.currency;

import com.strangersprings.zpr.client.dto.currency.CurrencyDTO;
import com.strangersprings.zpr.client.model.Currency;
import com.strangersprings.zpr.client.model.CurrencyType;

import java.time.LocalDateTime;
import java.util.List;

public interface CurrencyMapper {

    Currency toCurrency(CurrencyType type, LocalDateTime timestamp, double price);

    List<CurrencyDTO> toCurrencyDTOs(List<Currency> currencies);

    CurrencyDTO toCurrencyDTO(Currency currency);
}
