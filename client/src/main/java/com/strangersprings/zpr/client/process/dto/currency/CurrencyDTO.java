package com.strangersprings.zpr.client.process.dto.currency;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CurrencyDTO {
    private long id;
    private double value;
    private String name;
}
