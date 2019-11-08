package com.strangersprings.zpr.client.model;

public enum CryptocurrencyType {
    BTC("Bitcoin"),
    ETH("Ethernum"),
    ZEC("ZCash"),
    LTC("LTC");

    private String name;

    CryptocurrencyType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
