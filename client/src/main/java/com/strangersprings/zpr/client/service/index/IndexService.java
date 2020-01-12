package com.strangersprings.zpr.client.service.index;

import com.strangersprings.zpr.client.dto.index.IndexDTO;
import com.strangersprings.zpr.client.dto.index.IndexesDTO;
import com.strangersprings.zpr.client.model.Currency;
import com.strangersprings.zpr.client.model.IndexEntry;
import com.strangersprings.zpr.client.repository.index.IndexEntryRepoProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class IndexService {

    private final IndexEntryRepoProxy repository;
    private final IndexEntryMapper mapper;
    private final IndexEntryTypeService typeService;

    @Autowired
    public IndexService(IndexEntryRepoProxy repository, IndexEntryMapper mapper, IndexEntryTypeService typeService) {
        this.repository = repository;
        this.mapper = mapper;
        this.typeService = typeService;
    }

    public List<IndexEntry> saveIndexes(IndexesDTO indexesDTO, List<Currency> currencies) {
        Map<IndexDTO, Currency> currencyMap = new HashMap<>();
        for (IndexDTO dto : indexesDTO.getIndexes()) {
            findCurrencyWithTypeName(currencies, dto.getType())
                    .ifPresent(value -> currencyMap.put(dto, value));
        }
        List<IndexEntry> indices = currencyMap.entrySet().stream()
                .flatMap(entry -> mapper.toIndices(toIndexWithTypeList(entry.getKey().getEntries()), entry.getValue()).stream())
                .collect(toList());
        return repository.saveAll(indices);
    }

    private Optional<Currency> findCurrencyWithTypeName(List<Currency> currencies, String name) {
        for (Currency c : currencies) {
            if (c.getType().getName().toLowerCase().equals(name.toLowerCase())) {
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }

    private List<IndexWithType> toIndexWithTypeList(List<com.strangersprings.zpr.client.dto.index.IndexEntry> entries) {
        List<IndexWithType> result = new ArrayList<>(entries.size());
        for (com.strangersprings.zpr.client.dto.index.IndexEntry entry : entries) {
            result.add(new IndexWithType(typeService.findTypeByName(entry.getKey()), entry.getValue()));
        }
        return result;
    }
}
