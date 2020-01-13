package com.strangersprings.zpr.client.api.aggregation;

import com.strangersprings.zpr.client.model.Aggregation;
import com.strangersprings.zpr.client.model.AggregationEntry;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UIAggregationMapperImpl implements UIAggregationMapper {
    @Override
    public UIAggregationDTO toUIAggregationDTO(Aggregation aggregation) {
        return UIAggregationDTO.builder()
                .id(aggregation.getId())
                .aggregationType(aggregation.getType().getName())
                .currencyType(aggregation.getFirst().getType().getName())
                .startTime(aggregation.getFirst().getTimestamp())
                .endTime(aggregation.getLast().getTimestamp())
                .value(getValueByKey(aggregation.getEntries(), "avg"))
                .max(getValueByKey(aggregation.getEntries(), "max"))
                .min(getValueByKey(aggregation.getEntries(), "min"))
                .build();
    }

    private double getValueByKey(List<AggregationEntry> entries, String key) {
        return entries.stream()
                .filter(item -> item.getType().getName().equals(key))
                .findFirst()
                .map(item -> item.getValue().doubleValue()).orElse(0.0);
    }
}
