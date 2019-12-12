package com.strangersprings.zpr.client.service.calc;

public class CurrencyDTO {
    public long id;
    public double price;
    public String name;

    public CurrencyDTO(long id, double price, String name) {
        this.id = id;
        this.price = price;
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
