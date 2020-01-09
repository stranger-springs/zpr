package com.strangersprings.zpr.client.service.apiclient;

import com.strangersprings.zpr.client.config.AppConfig;
import com.strangersprings.zpr.client.config.CurrencyConfig;
import com.strangersprings.zpr.client.dto.currency.CurrenciesDTO;
import com.strangersprings.zpr.client.dto.currency.CurrencyDTO;
import com.strangersprings.zpr.client.model.CurrencyData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.strangersprings.zpr.client.service.Utils.getCurrentTimestamp;

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
    public CurrenciesDTO getCurrencies() {
        Map<String, CurrencyData> fetchedData = new HashMap(fetchers.size());
        for (Map.Entry<String, CurrencyDataFetcher> entry : fetchers.entrySet()) {
            fetchedData.put(entry.getKey(), entry.getValue().getData());
        }

        long id = getCurrentTimestamp().toEpochSecond(ZoneOffset.UTC);
        List<CurrencyDTO> currencyDTOList = fetchedData.entrySet().stream()
                .map(entry -> new CurrencyDTO(id, entry.getValue().getPrice(), entry.getKey()))
                .collect(Collectors.toList());
        return new CurrenciesDTO(currencyDTOList);
    }
}
