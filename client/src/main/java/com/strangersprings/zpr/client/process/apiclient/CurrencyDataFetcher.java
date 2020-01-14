package com.strangersprings.zpr.client.process.apiclient;

import com.strangersprings.zpr.client.model.CurrencyData;
import org.springframework.http.HttpEntity;

import java.util.Objects;

public class CurrencyDataFetcher {
    private final String name;
    private final String url;
    private final DataFetcher<CurrencyData> fetcher;

    public CurrencyDataFetcher(String name, String url) {
        this.name = name;
        this.url = url;
        this.fetcher = new DataFetcher<>(url, HttpEntity.EMPTY, CurrencyData.class);
    }

    public CurrencyData getData() {
        return Objects.requireNonNull(fetcher.getCurrentData().getBody());
    }

}
