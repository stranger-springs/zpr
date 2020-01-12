package com.strangersprings.zpr.client.dto.aggregation;

import lombok.Data;

import java.util.List;

@Data
public class AggregationDTO {
    private List<CurrencyAggregationDTO> result;
}
