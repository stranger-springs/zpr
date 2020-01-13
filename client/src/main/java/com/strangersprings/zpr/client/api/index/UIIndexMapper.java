package com.strangersprings.zpr.client.api.index;

import com.strangersprings.zpr.client.model.IndexEntry;

import java.util.List;

import static java.util.stream.Collectors.toList;

public interface UIIndexMapper {
    UIIndexDTO toUIIndexDTO(IndexEntry indexEntry);

    default List<UIIndexDTO> toUIIndexDTOs(List<IndexEntry> indexEntryList) {
        return indexEntryList.stream()
                .map(this::toUIIndexDTO)
                .collect(toList());
    }
}
