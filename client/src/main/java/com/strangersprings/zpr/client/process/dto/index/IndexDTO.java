package com.strangersprings.zpr.client.process.dto.index;

import lombok.Data;

import java.util.List;

@Data
public class IndexDTO {
    private String type;
    private List<IndexEntry> entries;
}
