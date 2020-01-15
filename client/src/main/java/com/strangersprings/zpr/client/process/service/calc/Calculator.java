package com.strangersprings.zpr.client.process.service.calc;

/**
 * klasa wczytuje biblioteka natywna C++ i wywoluje metody potrzebne do wyliczania agregacji oraz wartosci wskaznikow
 */
public class Calculator {

    static {
        System.loadLibrary("native");
    }

    public Calculator(String config) {
        init(config);
    }

    public String calcIndex(String input) {
        return updateIndex(input);
    }

    public String calcAggregation() {
        return updateAggregation();
    }

    private native void init(String config);

    private native String updateAggregation();

    private native String updateIndex(String data);
}
