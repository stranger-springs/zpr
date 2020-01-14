package com.strangersprings.zpr.client.process.dto.aggregation;

import lombok.Data;

@Data
public class CurrencyAggregationDTO {
    private String key;
    private StatDTO value;
}
