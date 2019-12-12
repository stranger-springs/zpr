package com.strangersprings.zpr.client.service.calc;

import java.util.Arrays;
import java.util.List;

public class Calculator {

    static {
        System.loadLibrary("native");
    }

    public Calculator() {
        init(Arrays.asList("bitcoin", "ethernum", "litecoin", "zcash"));
    }

    public void sayHelloFromCpp() {
        sayHello();
    }

    public List<CurrencyIndicesDTO> getIndicators(List<CurrencyDTO> currencyDTOS) {
        return calculateAll(currencyDTOS);
    }

    private native void sayHello();

    private native void init(List<String> names);

    private native List<CurrencyIndicesDTO> calculateAll(List<CurrencyDTO> currencyDTOS);
}
