package com.strangersprings.zpr.client.dto.aggregation;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class AggregationDTO {
    private List<CurrencyAggregationDTO> results = new ArrayList<>();
}
