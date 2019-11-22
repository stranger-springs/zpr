package com.strangersprings.zpr.client.service.calc;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @BeforeClass
    public static void setUpClass() {
        // .. load other dependent libraries
        System.loadLibrary("native");
    }

    @Test
    void getIndicators() {
        Calculator calculator = new Calculator();

        double value = calculator.getIndicators(new CurrencyDTO(1L, 10));
        assertEquals(12, value);
    }
}