package com.strangersprings.zpr.client.api.aggregation;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UIAggregationService {

    public List<UIAggregationDTO> getHistoricalData(String currencyType, String aggregationType, LocalDateTime startDate, LocalDateTime endDate) {
        //mock
        List<UIAggregationDTO> list = new ArrayList<>();
        list.add(generateItem(currencyType, aggregationType, startDate, endDate));
        list.add(generateItem(currencyType, aggregationType, startDate.plusHours(1), endDate.plusHours(1)));
        list.add(generateItem(currencyType, aggregationType, startDate.plusHours(2), endDate.plusHours(2)));
        list.add(generateItem(currencyType, aggregationType, startDate.plusHours(3), endDate.plusHours(3)));
        return list;
    }

    private UIAggregationDTO generateItem(String currencyType, String aggregationType, LocalDateTime startTime, LocalDateTime endTime) {
        return UIAggregationDTO.builder()
                .id(1L)
                .value(8000.00)
                .currencyType(currencyType)
                .aggregationType(aggregationType)
                .startTime(startTime)
                .endTime(endTime)
                .max(10000.00)
                .min(5000.00)
                .build();
    }
}
