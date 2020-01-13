package com.strangersprings.zpr.client.api.aggregation;

import com.strangersprings.zpr.client.repository.aggregation.AggregationRepoProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UIAggregationService {
    private final int FETCH_SIZE = 150;
    private final AggregationRepoProxy repository;
    private final UIAggregationMapper mapper;

    @Autowired
    public UIAggregationService(AggregationRepoProxy repository, UIAggregationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<UIAggregationDTO> getHistoricalData(String currencyType, String aggregationType, LocalDateTime startDate, LocalDateTime endDate) {
        return mapper.toUIAggregationDTOs(repository.findAggregationsWithLimit(currencyType, aggregationType, startDate, endDate, FETCH_SIZE));
    }

}
