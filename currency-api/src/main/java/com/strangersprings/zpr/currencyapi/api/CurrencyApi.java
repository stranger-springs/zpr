package com.strangersprings.zpr.currencyapi.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/currency")
public class CurrencyApi {

    @GetMapping("/bitcoin")
    public CurrencyData getBitcoin() {
        return CurrencyData.builder()
                .ask(0f)
                .bid(0f)
                .price(getRandomPrice(8500f, 9000f))
                .exchange("bitcoinusd")
                .pair("abc")
                .build();
    }

    @GetMapping("/ethernum")
    public CurrencyData getEthernum() {
        return CurrencyData.builder()
                .ask(0f)
                .bid(0f)
                .price(getRandomPrice(150f, 300f))
                .exchange("ethernumusd")
                .pair("abc")
                .build();
    }

    @GetMapping("/litecoin")
    public CurrencyData getLitecoin() {
        return CurrencyData.builder()
                .ask(0f)
                .bid(0f)
                .price(getRandomPrice(61f, 250f))
                .exchange("litecoinusd")
                .pair("abc")
                .build();
    }

    @GetMapping("/zcash")
    public CurrencyData getZCash() {
        return CurrencyData.builder()
                .ask(0f)
                .bid(0f)
                .price(getRandomPrice(40f, 80f))
                .exchange("zcashusd")
                .pair("abc")
                .build();
    }

    private float getRandomPrice(float leftLimit, float rightLimit) {
        float randomFloat = ThreadLocalRandom.current().nextFloat();
        return leftLimit + randomFloat * (rightLimit - leftLimit);
    }
}
