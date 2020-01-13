package com.strangersprings.zpr.client.api.index;

import com.strangersprings.zpr.client.model.IndexEntry;
import org.springframework.stereotype.Component;

@Component
public class UIIndexMapperImpl implements UIIndexMapper {

    @Override
    public UIIndexDTO toUIIndexDTO(IndexEntry indexEntry) {
        return UIIndexDTO.builder()
                .id(indexEntry.getId())
                .timestamp(indexEntry.getCurrency().getTimestamp())
                .currencyType(indexEntry.getCurrency().getType().getName())
                .indexType(indexEntry.getType().getName())
                .value(indexEntry.getValue().doubleValue())
                .build();
    }

}
