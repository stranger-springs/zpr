package com.strangersprings.zpr.client.api.aggregation;

import com.strangersprings.zpr.client.model.Aggregation;

import java.util.List;

import static java.util.stream.Collectors.toList;

public interface UIAggregationMapper {

    UIAggregationDTO toUIAggregationDTO(Aggregation aggregation);

    default List<UIAggregationDTO> toUIAggregationDTOs(List<Aggregation> aggregationList) {
        return aggregationList.stream()
                .map(this::toUIAggregationDTO)
                .collect(toList());
    }
}
