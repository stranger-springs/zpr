package com.strangersprings.zpr.client.api.index;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UIIndexDTO {

    private Long id;
    private LocalDateTime timestamp;
    private double value;
    private String currencyType;
    private String indexType;

}
