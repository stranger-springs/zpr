package com.strangersprings.zpr.client.service;

import com.strangersprings.zpr.client.dto.currency.CurrenciesDTO;
import com.strangersprings.zpr.client.repository.CryptoCurrencyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {

    private final CryptoCurrencyDAO cryptoCurrencyDAO;

    @Autowired
    private CurrencyService(CryptoCurrencyDAO cryptoCurrencyDAO){
        this.cryptoCurrencyDAO = cryptoCurrencyDAO;
    }

    public void saveCurrencies(CurrenciesDTO currencies) {
        cryptoCurrencyDAO.saveCurrencies(currencies);
    }
}
