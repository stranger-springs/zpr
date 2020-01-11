package com.strangersprings.zpr.client.service.currency;

import com.strangersprings.zpr.client.model.CurrencyType;
import com.strangersprings.zpr.client.repository.currency.CurrencyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CurrencyTypeService {

    private final CurrencyTypeRepository repository;

    @Autowired
    public CurrencyTypeService(CurrencyTypeRepository repository) {
        this.repository = repository;
    }

    public CurrencyType findTypeByName(String name) {
        return getTypes().stream()
                .filter(currencyType -> currencyType.getName().toLowerCase().equals(name.toLowerCase()))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("No type with name " + name.toLowerCase()));
    }

    @Cacheable("currency_types")
    public List<CurrencyType> getTypes() {
        return repository.findAll();
    }
}
