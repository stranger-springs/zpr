package com.strangersprings.zpr.client.service.index;

import com.strangersprings.zpr.client.model.Currency;
import com.strangersprings.zpr.client.model.IndexEntry;
import com.strangersprings.zpr.client.model.IndexEntryType;

import java.util.List;

public interface IndexEntryMapper {

    List<IndexEntry> toIndices(List<IndexWithType> dto, Currency currency);

    IndexEntry toIndex(Currency currency, IndexEntryType type, double value);

}
