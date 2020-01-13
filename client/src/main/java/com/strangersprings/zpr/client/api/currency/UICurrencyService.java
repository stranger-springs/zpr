package com.strangersprings.zpr.client.api.currency;

import com.strangersprings.zpr.client.repository.currency.CurrencyRepoProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        //mock
        List<UICurrencyDTO> list = new ArrayList<>();
        list.add(new UICurrencyDTO(1L, LocalDateTime.now(), 8000.00, type));
        list.add(new UICurrencyDTO(2L, LocalDateTime.now(), 9000.00, type));
        list.add(new UICurrencyDTO(3L, LocalDateTime.now(), 8500.00, type));
        return list;
        //return mapper.toUICurrencyDTOs(repository.findAll(type));
    }

    public UICurrencyDTO getLastOne(String type) {
        //mock
        return new UICurrencyDTO(1L, LocalDateTime.now(), 7000.00, type);
        //return mapper.toUICurrencyDTO(repository.getLastOne(type));
    }
}
