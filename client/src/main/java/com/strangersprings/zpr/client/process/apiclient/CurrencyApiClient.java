package com.strangersprings.zpr.client.process.apiclient;

import com.strangersprings.zpr.client.model.CurrencyData;

import java.util.Map;

public interface CurrencyApiClient {
    Map<String, CurrencyData> getCurrencies();
}
