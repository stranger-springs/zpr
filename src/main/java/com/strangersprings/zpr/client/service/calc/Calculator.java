package com.strangersprings.zpr.client.service.calc;

public class Calculator {

    static {
        System.loadLibrary("native");
    }

    public Calculator() {
        System.out.println("Library path: " + System.getProperty("java.library.path"));
        sayHello();
    }

    private native void sayHello();
}
