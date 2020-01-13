package com.strangersprings.zpr.client.api.aggregation;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UIAggregationDTO {

    private Long id;
    private double value;
    private double min;
    private double max;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String currencyType;
    private String aggregationType;
}
