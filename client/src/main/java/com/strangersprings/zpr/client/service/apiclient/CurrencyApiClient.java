package com.strangersprings.zpr.client.service.apiclient;

import com.strangersprings.zpr.client.model.CurrencyData;

import java.util.Map;

public interface CurrencyApiClient {
    Map<String, CurrencyData> getCurrencies();
}
