package com.strangersprings.zpr.client.repository;

import com.strangersprings.zpr.client.dto.currency.CurrenciesDTO;

public interface CryptoCurrencyDAO {

    void saveCurrencies(CurrenciesDTO currenciesDTO);
}
