package com.strangersprings.zpr.client.service.aggregation;

import com.strangersprings.zpr.client.model.AggregationType;
import com.strangersprings.zpr.client.repository.aggregation.AggregationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AggregationTypeService {

    private final AggregationTypeRepository repository;

    @Autowired
    public AggregationTypeService(AggregationTypeRepository repository) {
        this.repository = repository;
    }

    public List<AggregationType> getTypes() {
        return repository.findAll();
    }
}
