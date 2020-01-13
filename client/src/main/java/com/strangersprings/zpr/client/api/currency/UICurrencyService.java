package com.strangersprings.zpr.client.api.currency;

import com.strangersprings.zpr.client.repository.currency.CurrencyRepoProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UICurrencyService {
    private final int FETCH_SIZE = 150;
    private final CurrencyRepoProxy repository;
    private final UICurrencyMapper mapper;

    @Autowired
    public UICurrencyService(CurrencyRepoProxy repository, UICurrencyMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<UICurrencyDTO> findAll(String type) {
        return mapper.toUICurrencyDTOs(repository.findAllByTypeWithLimit(type, FETCH_SIZE));
    }

    public UICurrencyDTO getLastOne(String type) {
        return mapper.toUICurrencyDTO(repository.getLastOne(type));
    }
}
