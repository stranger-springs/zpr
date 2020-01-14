package com.strangersprings.zpr.client.process.dto.aggregation;

import lombok.Data;

import java.util.List;

@Data
public class StatDTO {
    private String id;
    private Long firstId;
    private Long lastId;
    private List<StatDTOEntry> stats;
}
