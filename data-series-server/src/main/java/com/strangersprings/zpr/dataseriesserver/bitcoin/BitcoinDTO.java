package com.strangersprings.zpr.dataseriesserver.bitcoin;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
public class BitcoinDTO {
    private Long id;
    private LocalDateTime timestamp;
    private BigDecimal price;
}
