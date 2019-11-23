package com.strangersprings.zpr.client.service.calc;

public class Calculator {

    static {
        System.loadLibrary("native");
    }

    public Calculator() {
        init();
    }

    public void sayHelloFromCpp() {
        sayHello();
    }

    public double getIndicators(CurrencyDTO currencyDTO) {
        return calculate(currencyDTO).value;
    }

    private native void sayHello();

    private native CurrencyIndicator calculate(CurrencyDTO currencyDTO);

    private native double bitcoinAverage();

    private native void init();

    public void insertBitcoinDTO(CurrencyDTO currencyDTO) {
        insertBitcoin(currencyDTO);
    }

    public double getBitcoinAverage() {
        return bitcoinAverage();
    }

    private native void insertBitcoin(CurrencyDTO currencyDTO);
}
