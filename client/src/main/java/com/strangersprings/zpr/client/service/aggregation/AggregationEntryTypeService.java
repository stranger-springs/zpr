package com.strangersprings.zpr.client.service.aggregation;

import com.strangersprings.zpr.client.model.AggregationEntryType;
import com.strangersprings.zpr.client.repository.aggregation.AggregationEntryTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AggregationEntryTypeService {

    private final AggregationEntryTypeRepository repository;

    @Autowired
    public AggregationEntryTypeService(AggregationEntryTypeRepository repository) {
        this.repository = repository;
    }

    public List<AggregationEntryType> getTypes() {
        return repository.findAll();
    }
}
