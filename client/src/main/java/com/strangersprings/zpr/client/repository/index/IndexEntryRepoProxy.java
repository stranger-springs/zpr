package com.strangersprings.zpr.client.repository.index;

import com.strangersprings.zpr.client.model.IndexEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IndexEntryRepoProxy {

    private final IndexEntryRepository repository;

    @Autowired
    public IndexEntryRepoProxy(IndexEntryRepository repository) {
        this.repository = repository;
    }

    public List<IndexEntry> saveAll(List<IndexEntry> indices) {
        return repository.saveAll(indices);
    }

    public List<IndexEntry> findAll(String currencyType, String indexType, int limit) {
        return repository.findIndicesByTypeWithLimit(currencyType.toLowerCase(), indexType.toLowerCase(), limit);
    }

    public IndexEntry getLastOne(String currencyType, String indexType) {
        return repository.findTopByCurrency_Type_NameAndTypeOrderByIdDesc(currencyType, indexType);
    }
}
