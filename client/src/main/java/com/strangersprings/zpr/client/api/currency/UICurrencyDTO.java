package com.strangersprings.zpr.client.api.currency;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UICurrencyDTO {

    private Long id;
    private LocalDateTime timestamp;
    private double price;
}
