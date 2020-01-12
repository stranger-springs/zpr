package com.strangersprings.zpr.client.service.index;

import com.strangersprings.zpr.client.model.IndexEntryType;
import com.strangersprings.zpr.client.repository.index.IndexEntryTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class IndexEntryTypeService {

    private final IndexEntryTypeRepository repository;

    @Autowired
    public IndexEntryTypeService(IndexEntryTypeRepository repository) {
        this.repository = repository;
    }

    public IndexEntryType findTypeByName(String name) {
        return getTypes().stream()
                .filter(currencyType -> currencyType.getName().toLowerCase().equals(name.toLowerCase()))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("No type with name " + name.toLowerCase()));
    }

    @Cacheable("index_types")
    public List<IndexEntryType> getTypes() {
        return repository.findAll();
    }

}
