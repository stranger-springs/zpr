package com.strangersprings.zpr.client.config;

import lombok.Data;

@Data
public class CurrencyConfig {
    private String name;
    private String endpoint;
    private String tableName;
}
