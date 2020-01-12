package com.strangersprings.zpr.client.service.aggregation;

import com.strangersprings.zpr.client.dto.aggregation.CurrencyAggregationDTO;
import com.strangersprings.zpr.client.dto.aggregation.StatDTO;
import com.strangersprings.zpr.client.dto.aggregation.StatDTOEntry;
import com.strangersprings.zpr.client.model.Aggregation;
import com.strangersprings.zpr.client.model.AggregationEntry;
import com.strangersprings.zpr.client.service.Utils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AggregationMapperImpl implements AggregationMapper {

    @Override
    public List<Aggregation> toAggregations(List<CurrencyAggregationDTO> aggregationDTOs, DataHolder holder) {
        return aggregationDTOs.stream()
                .map(dto -> toAggregation(dto, holder))
                .collect(Collectors.toList());
    }

    @Override
    public Aggregation toAggregation(CurrencyAggregationDTO dto, DataHolder holder) {
        Aggregation aggregation = new Aggregation();
        aggregation.setType(holder.findAggregationType(dto.getValue().getId()));
        aggregation.setFirst(holder.findCurrency(dto.getValue().getFirstId()));
        aggregation.setLast(holder.findCurrency(dto.getValue().getLastId()));
        aggregation.setEntries(toAggregationEntries(aggregation, dto.getValue(), holder));
        return aggregation;
    }

    private List<AggregationEntry> toAggregationEntries(Aggregation aggregation, StatDTO statDTO, DataHolder holder) {
        List<AggregationEntry> aggregationEntries = new ArrayList<>();
        for (StatDTOEntry dtoEntry : statDTO.getStats()) {
            AggregationEntry entry = new AggregationEntry();
            entry.setAggregation(aggregation);
            entry.setType(holder.findAggregationEntryType(dtoEntry.getKey()));
            entry.setValue(Utils.getRoundedPrice(dtoEntry.getValue()));
            aggregationEntries.add(entry);
        }
        return aggregationEntries;
    }
}
