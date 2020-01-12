package com.strangersprings.zpr.client.service.currency;

import com.strangersprings.zpr.client.model.Currency;
import com.strangersprings.zpr.client.model.CurrencyData;
import com.strangersprings.zpr.client.repository.currency.CurrencyRepoProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Service
public class CurrencyService {

    private final CurrencyRepoProxy repository;
    private final CurrencyMapper mapper;
    private final CurrencyTypeService typeService;

    @Autowired
    private CurrencyService(CurrencyRepoProxy repository, CurrencyMapper mapper, CurrencyTypeService typeService) {
        this.repository = repository;
        this.mapper = mapper;
        this.typeService = typeService;
    }

    public List<Currency> saveCurrencies(Map<String, CurrencyData> currencies, LocalDateTime timestamp) {
        List<Currency> currenciesToSave = currencies.entrySet().stream()
                .map(entry -> mapper.toCurrency(typeService.findTypeByName(entry.getKey()), timestamp, entry.getValue().getPrice()))
                .collect(toList());
        return repository.saveAll(currenciesToSave);
    }

    public List<Currency> findAllByIds(List<Long> ids){
        return repository.findAllByIds(ids);
    }
}
