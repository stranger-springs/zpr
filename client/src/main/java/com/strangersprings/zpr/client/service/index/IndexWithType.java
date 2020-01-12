package com.strangersprings.zpr.client.service.index;


import com.strangersprings.zpr.client.model.IndexEntryType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class IndexWithType {
    private IndexEntryType type;
    private double value;
}
