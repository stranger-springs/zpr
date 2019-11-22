package com.strangersprings.zpr.client.service.calc;

public class Calculator {

    static {
        System.loadLibrary("native");
    }

    public void sayHelloFromCpp() {
        sayHello();
    }

    public double getIndicators(CurrencyDTO currencyDTO) {
        return calculate(currencyDTO).value;
    }

    private native void sayHello();

    private native CurrencyIndicator calculate(CurrencyDTO currencyDTO);
}
