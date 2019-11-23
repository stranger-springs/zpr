package com.strangersprings.zpr.dataseriesserver.ethernum;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
public class EthernumDTO {
    private Long id;
    private LocalDateTime timestamp;
    private BigDecimal price;
}
