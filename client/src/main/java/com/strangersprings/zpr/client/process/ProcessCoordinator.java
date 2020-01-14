package com.strangersprings.zpr.client.process;

import com.strangersprings.zpr.client.model.Currency;
import com.strangersprings.zpr.client.model.CurrencyData;
import com.strangersprings.zpr.client.process.apiclient.CurrencyApiClient;
import com.strangersprings.zpr.client.process.dto.aggregation.AggregationDTO;
import com.strangersprings.zpr.client.process.dto.currency.CurrenciesDTO;
import com.strangersprings.zpr.client.process.dto.index.IndexesDTO;
import com.strangersprings.zpr.client.process.service.aggregation.AggregationService;
import com.strangersprings.zpr.client.process.service.calc.CalculationService;
import com.strangersprings.zpr.client.process.service.currency.CurrencyMapper;
import com.strangersprings.zpr.client.process.service.currency.CurrencyService;
import com.strangersprings.zpr.client.process.service.index.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProcessCoordinator {

    private final CurrencyApiClient currencyApiClient;
    private final AggregationService aggregationService;
    private final IndexService indexService;
    private final CurrencyService currencyService;

    private final CalculationService calculationService;
    private final CurrencyMapper currencyMapper;

    @Autowired
    public ProcessCoordinator(CurrencyApiClient currencyApiClient, AggregationService aggregationService,
                              IndexService indexService, CurrencyService currencyService, CurrencyMapper currencyMapper) {

        this.currencyApiClient = currencyApiClient;
        this.aggregationService = aggregationService;
        this.indexService = indexService;
        this.currencyService = currencyService;
        this.calculationService = new CalculationService(Utils.loadConfigFile(this.getClass(), "Config.json"));
        this.currencyMapper = currencyMapper;
    }

    @Scheduled(fixedRate = 5000, initialDelay = 5000)
    private void update() {
        processCurrencies();
    }

    private void processCurrencies() {
        Map<String, CurrencyData> currencies = currencyApiClient.getCurrencies();
        List<Currency> savedCurrencies = currencyService.saveCurrencies(currencies, Utils.getCurrentTimestamp());
        IndexesDTO indexesDTO = calculationService.updateIndex(new CurrenciesDTO(currencyMapper.toCurrencyDTOs(savedCurrencies)));
        indexService.saveIndexes(indexesDTO, savedCurrencies);
        AggregationDTO aggregationDTO = calculationService.updateAggregation();
        aggregationService.saveAggregation(aggregationDTO);
    }
}

