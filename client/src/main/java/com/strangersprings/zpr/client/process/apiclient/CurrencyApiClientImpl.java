package com.strangersprings.zpr.client.process.apiclient;

import com.strangersprings.zpr.client.config.AppConfig;
import com.strangersprings.zpr.client.config.CurrencyConfig;
import com.strangersprings.zpr.client.model.CurrencyData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CurrencyApiClientImpl implements CurrencyApiClient {

    private final AppConfig appConfig;
    private final Map<String, CurrencyDataFetcher> fetchers;

    @Autowired
    public CurrencyApiClientImpl(AppConfig appConfig) {
        this.appConfig = appConfig;
        this.fetchers = new HashMap<>();
        for (CurrencyConfig config : this.appConfig.getCurrencies()) {
            String name = config.getName();
            String url = appConfig.getApiurl() + config.getEndpoint();
            fetchers.put(name, new CurrencyDataFetcher(name, url));
        }
    }

    @Override
    public Map<String, CurrencyData> getCurrencies() {
        Map<String, CurrencyData> fetchedData = new HashMap(fetchers.size());
        for (Map.Entry<String, CurrencyDataFetcher> entry : fetchers.entrySet()) {
            fetchedData.put(entry.getKey(), entry.getValue().getData());
        }
        return fetchedData;
    }
}
