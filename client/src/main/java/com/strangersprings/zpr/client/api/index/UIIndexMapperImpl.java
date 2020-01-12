package com.strangersprings.zpr.client.api.index;

import com.strangersprings.zpr.client.model.IndexEntry;
import org.springframework.stereotype.Component;

@Component
public class UIIndexMapperImpl implements UIIndexMapper {

    @Override
    public UIIndexDTO toUIIndexDTO(IndexEntry indexEntry) {
        return new UIIndexDTO(indexEntry.getId(), indexEntry.getCurrency().getTimestamp(),
                indexEntry.getValue().doubleValue());
    }
}
