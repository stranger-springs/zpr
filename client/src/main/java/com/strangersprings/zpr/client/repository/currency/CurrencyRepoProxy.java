package com.strangersprings.zpr.client.repository.currency;

import com.strangersprings.zpr.client.model.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CurrencyRepoProxy {

    private final CurrencyRepository repository;

    @Autowired
    public CurrencyRepoProxy(CurrencyRepository repository) {
        this.repository = repository;
    }

    public List<Currency> saveAll(List<Currency> currencies) {
        return this.repository.saveAll(currencies);
    }

    public List<Currency> findAllByIds(List<Long> ids) {
        return repository.findAllById(ids);
    }

    public List<Currency> findAll(String type) { return null;}

    public Currency getLastOne(String type) {return null;}
}
