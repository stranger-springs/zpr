package com.strangersprings.zpr.client.service.index;

import com.strangersprings.zpr.client.model.Currency;
import com.strangersprings.zpr.client.model.IndexEntry;
import com.strangersprings.zpr.client.model.IndexEntryType;
import com.strangersprings.zpr.client.service.Utils;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class IndexEntryMapperImpl implements IndexEntryMapper {

    @Override
    public List<IndexEntry> toIndices(List<IndexWithType> dto, Currency currency) {
        return dto.stream()
                .map(indexWithType -> toIndex(currency, indexWithType.getType(), indexWithType.getValue()))
                .collect(toList());
    }

    @Override
    public IndexEntry toIndex(Currency currency, IndexEntryType type, double value) {
        IndexEntry indexEntry = new IndexEntry();
        indexEntry.setCurrency(currency);
        indexEntry.setType(type);
        indexEntry.setValue(Utils.getRoundedValue(value));
        return indexEntry;
    }
}
