package com.strangersprings.zpr.client.service;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.strangersprings.zpr.client.dto.aggregation.AggregationDTO;
import com.strangersprings.zpr.client.dto.currency.CurrenciesDTO;
import com.strangersprings.zpr.client.dto.index.IndexesDTO;
import com.strangersprings.zpr.client.service.apiclient.CurrencyApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class Coordinator {

    private final CurrencyApiClient currencyApiClient;
    private final AggregationService aggregationService;
    private final IndexService indexService;
    private final CurrencyService currencyService;

    private final CalculationService calculationService;

    @Autowired
    public Coordinator(CurrencyApiClient currencyApiClient, AggregationService aggregationService,
                       IndexService indexService, CurrencyService currencyService) {

        this.currencyApiClient = currencyApiClient;
        this.aggregationService = aggregationService;
        this.indexService = indexService;
        this.currencyService = currencyService;
        this.calculationService = new CalculationService(loadConfigFile("Config.json"));
    }

    private String loadConfigFile(String filename) {
        String config = "";
        try {
            InputStream inputStream = getClass()
                    .getClassLoader().getResourceAsStream(filename);
            config = CharStreams.toString(new InputStreamReader(
                    inputStream, Charsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }

    @Scheduled(fixedRate = 5000, initialDelay = 5000)
    private void update() {
        CurrenciesDTO currencies = currencyApiClient.getCurrencies();
        IndexesDTO indexesDTO = calculationService.updateIndex(currencies);
        currencyService.saveCurrencies(currencies);
        indexService.saveIndexes(indexesDTO);
        AggregationDTO aggregationDTO = calculationService.updateAggregation();
        aggregationService.saveAggregation(aggregationDTO);
    }
}

