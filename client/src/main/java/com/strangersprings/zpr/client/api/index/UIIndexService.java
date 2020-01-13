package com.strangersprings.zpr.client.api.index;

import com.strangersprings.zpr.client.repository.index.IndexEntryRepoProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UIIndexService {
    private final int FETCH_SIZE = 150;
    private final IndexEntryRepoProxy repository;
    private final UIIndexMapper mapper;

    @Autowired
    public UIIndexService(IndexEntryRepoProxy repository, UIIndexMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<UIIndexDTO> findAll(String currencyType, String indexType) {
        return mapper.toUIIndexDTOs(repository.findAll(currencyType, indexType, FETCH_SIZE));
    }

    public UIIndexDTO getLastOne(String currencyType, String indexType) {
        return mapper.toUIIndexDTO(repository.getLastOne(currencyType, indexType));
    }
}
