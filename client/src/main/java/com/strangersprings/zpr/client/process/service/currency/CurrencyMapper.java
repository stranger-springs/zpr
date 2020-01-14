package com.strangersprings.zpr.client.process.service.currency;

import com.strangersprings.zpr.client.model.Currency;
import com.strangersprings.zpr.client.model.CurrencyType;
import com.strangersprings.zpr.client.process.dto.currency.CurrencyDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface CurrencyMapper {

    Currency toCurrency(CurrencyType type, LocalDateTime timestamp, double price);

    List<CurrencyDTO> toCurrencyDTOs(List<Currency> currencies);

    CurrencyDTO toCurrencyDTO(Currency currency);
}
