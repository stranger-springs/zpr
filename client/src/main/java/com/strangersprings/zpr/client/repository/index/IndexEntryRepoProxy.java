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
}
