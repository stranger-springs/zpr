package com.strangersprings.zpr.client.service;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.strangersprings.zpr.client.dto.aggregation.AggregationDTO;
import com.strangersprings.zpr.client.dto.currency.CurrenciesDTO;
import com.strangersprings.zpr.client.dto.index.IndexesDTO;
import com.strangersprings.zpr.client.model.Currency;
import com.strangersprings.zpr.client.model.CurrencyData;
import com.strangersprings.zpr.client.service.apiclient.CurrencyApiClient;
import com.strangersprings.zpr.client.service.calc.CalculationService;
import com.strangersprings.zpr.client.service.currency.CurrencyMapper;
import com.strangersprings.zpr.client.service.currency.CurrencyService;
import com.strangersprings.zpr.client.service.index.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

@Service
public class Coordinator {

    private final CurrencyApiClient currencyApiClient;
    private final AggregationService aggregationService;
    private final IndexService indexService;
    private final CurrencyService currencyService;

    private final CalculationService calculationService;
    private final CurrencyMapper currencyMapper;

    @Autowired
    public Coordinator(CurrencyApiClient currencyApiClient, AggregationService aggregationService,
                       IndexService indexService, CurrencyService currencyService, CurrencyMapper currencyMapper) {

        this.currencyApiClient = currencyApiClient;
        this.aggregationService = aggregationService;
        this.indexService = indexService;
        this.currencyService = currencyService;
        this.calculationService = new CalculationService(loadConfigFile("Config.json"));
        this.currencyMapper = currencyMapper;
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
        Map<String, CurrencyData> currencies = currencyApiClient.getCurrencies();
        List<Currency> savedCurrencies = currencyService.saveCurrencies(currencies, Utils.getCurrentTimestamp());
        IndexesDTO indexesDTO = calculationService.updateIndex(new CurrenciesDTO(currencyMapper.toCurrencyDTOs(savedCurrencies)));
        indexService.saveIndexes(indexesDTO);
        AggregationDTO aggregationDTO = calculationService.updateAggregation();
        aggregationService.saveAggregation(aggregationDTO);
    }
}

