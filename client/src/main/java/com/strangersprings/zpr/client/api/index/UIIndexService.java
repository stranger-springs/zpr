package com.strangersprings.zpr.client.api.index;

import com.strangersprings.zpr.client.model.IndexEntry;
import com.strangersprings.zpr.client.repository.index.IndexEntryRepoProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
        //mock
        List<UIIndexDTO> list = new ArrayList<>();
        list.add(new UIIndexDTO(1L, LocalDateTime.now(), 200.00, currencyType, indexType));
        list.add(new UIIndexDTO(2L, LocalDateTime.now(), 300.00, currencyType, indexType));
        list.add(new UIIndexDTO(3L, LocalDateTime.now(), 400.00, currencyType, indexType));
        return list;
        //return mapper.toUIIndexDTOs(repository.findAll(currencyType, indexType));
    }

    public UIIndexDTO getLastOne(String currencyType, String indexType) {
        //mock
        return new UIIndexDTO(1L, LocalDateTime.now(), 100.00, currencyType, indexType);
        //return mapper.toUIIndexDTO(repository.getLastOne(currencyType, indexType));
    }
}
