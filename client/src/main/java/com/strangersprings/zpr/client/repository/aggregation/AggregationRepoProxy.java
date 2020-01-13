package com.strangersprings.zpr.client.repository.aggregation;

import com.strangersprings.zpr.client.model.Aggregation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
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

    public List<Aggregation> findAggregationsWithLimit(String currencyType, String aggregationType, LocalDateTime startDate, LocalDateTime endDate, int limit) {
        return repository.findAggregationsWithLimit(currencyType, aggregationType, startDate, endDate, limit);
    }
}
