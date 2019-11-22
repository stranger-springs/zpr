package com.strangersprings.zpr.client.model;


import lombok.*;

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
