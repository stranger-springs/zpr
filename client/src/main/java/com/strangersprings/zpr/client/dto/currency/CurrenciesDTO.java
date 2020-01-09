package com.strangersprings.zpr.client.dto.currency;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CurrenciesDTO {
    private List<CurrencyDTO> currencies;
}
