package com.strangersprings.zpr.client.api.currency;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UICurrencyDTO {

    private Long id;
    private LocalDateTime timestamp;
    private double price;
    private String type;
}
