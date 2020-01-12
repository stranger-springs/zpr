package com.strangersprings.zpr.client.api.index;

import com.strangersprings.zpr.client.model.IndexEntry;
import com.strangersprings.zpr.client.repository.index.IndexEntryRepoProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class UIIndexService {

    private final IndexEntryRepoProxy repository;
    private final UIIndexMapper mapper;

    @Autowired
    public UIIndexService(IndexEntryRepoProxy repository, UIIndexMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<UIIndexDTO> findAll(String currencyType, String indexType) {
        return repository.findAll(currencyType, indexType).stream()
                .map( index -> mapper.toUIIndexDTO(index)).collect(toList());
    }

    public UIIndexDTO getLastOne(String currencyType, String indexType) {
        return mapper.toUIIndexDTO(repository.getLastOne(currencyType, indexType));
    }
}
