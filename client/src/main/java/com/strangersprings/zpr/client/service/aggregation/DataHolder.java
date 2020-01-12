package com.strangersprings.zpr.client.service.aggregation;

import com.strangersprings.zpr.client.model.AggregationEntryType;
import com.strangersprings.zpr.client.model.AggregationType;
import com.strangersprings.zpr.client.model.Currency;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class DataHolder {
    private Map<Long, Currency> currencyMap;
    private Map<String, AggregationType> aggregationTypeMap;
    private Map<String, AggregationEntryType> aggregationEntryTypeMap;

    DataHolder(List<Currency> currencies, List<AggregationType> aggregationTypes, List<AggregationEntryType> aggregationEntryTypes) {
        this.currencyMap = currencies.stream().collect(Collectors.toMap(Currency::getId, item -> item));
        this.aggregationTypeMap = aggregationTypes.stream()
                .collect(Collectors.toMap(item -> item.getName().toLowerCase(), item -> item));
        this.aggregationEntryTypeMap = aggregationEntryTypes.stream()
                .collect(Collectors.toMap(item -> item.getName().toLowerCase(), item -> item));
    }

    Currency findCurrency(Long id) {
        return currencyMap.get(id);
    }

    AggregationType findAggregationType(String name) {
        return aggregationTypeMap.get(name.toLowerCase());
    }

    AggregationEntryType findAggregationEntryType(String name) {
        return aggregationEntryTypeMap.get(name.toLowerCase());

    }
}
