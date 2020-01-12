package com.strangersprings.zpr.client.repository.aggregation;

import com.strangersprings.zpr.client.model.Aggregation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AggregationRepoProxy {

    private final AggregationRepository repository;

    @Autowired
    public AggregationRepoProxy(AggregationRepository repository) {
        this.repository = repository;
    }

    public List<Aggregation> saveAll(List<Aggregation> aggregations) {
        return repository.saveAll(aggregations);
    }
}
