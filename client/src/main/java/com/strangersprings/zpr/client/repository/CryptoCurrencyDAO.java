package com.strangersprings.zpr.client.repository;

import com.strangersprings.zpr.client.model.Bitcoin;
import com.strangersprings.zpr.client.model.Ethernum;
import com.strangersprings.zpr.client.model.Litecoin;
import com.strangersprings.zpr.client.model.ZCash;

public interface CryptoCurrencyDAO {

    void saveCurrencies(Bitcoin bitcoin, Ethernum ethernum, Litecoin litecoin, ZCash zCash);
}
