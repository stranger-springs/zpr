package com.strangersprings.zpr.client.api.index;

import com.strangersprings.zpr.client.model.IndexEntry;

public interface UIIndexMapper {

    UIIndexDTO toUIIndexDTO(IndexEntry indexEntry);
}
