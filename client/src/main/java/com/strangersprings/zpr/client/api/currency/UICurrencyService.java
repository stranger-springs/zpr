package com.strangersprings.zpr.client.api.currency;

import com.strangersprings.zpr.client.repository.currency.CurrencyRepoProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class UICurrencyService {

    private final CurrencyRepoProxy repository;
    private final UICurrencyMapper mapper;

    @Autowired
    public UICurrencyService(CurrencyRepoProxy repository, UICurrencyMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<UICurrencyDTO> findAll(String type) {
        return repository.findAll(type).stream()
                .map( currency -> mapper.toUICurrencyDTO(currency)).collect(toList());
    }

    public UICurrencyDTO getLastOne(String type) {
        return mapper.toUICurrencyDTO(repository.getLastOne(type));
    }
}
