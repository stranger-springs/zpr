package com.strangersprings.zpr.client.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
public class Litecoin {

    private Long id;

    private LocalDateTime timestamp;

    private BigDecimal price;
}
