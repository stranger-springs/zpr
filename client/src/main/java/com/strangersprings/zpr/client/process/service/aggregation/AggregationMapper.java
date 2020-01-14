package com.strangersprings.zpr.client.process.service.aggregation;

import com.strangersprings.zpr.client.model.Aggregation;
import com.strangersprings.zpr.client.process.dto.aggregation.CurrencyAggregationDTO;

import java.util.List;

public interface AggregationMapper {

    List<Aggregation> toAggregations(List<CurrencyAggregationDTO> aggregationDTOs, DataHolder holder);

    Aggregation toAggregation(CurrencyAggregationDTO dto, DataHolder holder);
}
