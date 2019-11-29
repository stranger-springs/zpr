package com.strangersprings.zpr.currencyapi.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurrencyData {

    private String exchange;

    private String pair;

    private float price;

    private float ask;

    private float bid;

}
