package com.strangersprings.zpr.client.service.aggregation;

import com.strangersprings.zpr.client.dto.aggregation.CurrencyAggregationDTO;
import com.strangersprings.zpr.client.model.Aggregation;
import com.strangersprings.zpr.client.model.AggregationType;
import com.strangersprings.zpr.client.model.Currency;

import java.util.List;

public interface AggregationMapper {

    List<Aggregation> toAggregations(List<CurrencyAggregationDTO> aggregationDTOs, DataHolder holder);

    Aggregation toAggregation(CurrencyAggregationDTO dto, DataHolder holder);
}
