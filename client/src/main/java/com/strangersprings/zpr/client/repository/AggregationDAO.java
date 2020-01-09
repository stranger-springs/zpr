package com.strangersprings.zpr.client.repository;

import com.strangersprings.zpr.client.dto.aggregation.AggregationDTO;

public interface AggregationDAO {
    void saveAggregation(AggregationDTO aggregationDTO);
}
