package com.strangersprings.zpr.currencyapi.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/currency")
public class CurrencyApi {

    /**
     * funkcja generujaca wartosci Bitcoina (BTC)
     *
     * @return wartosc kryptowaluty
     */

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

    /**
     * funkcja generujaca wartosci Ethernum (ETH)
     *
     * @return wartosc kryptowaluty
     */

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

    /**
     * funkcja generujaca wartosci Litecoin (LTC)
     *
     * @return wartosc kryptowaluty
     */

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

    /**
     * funkcja generujaca wartosci ZCash (ZEC)
     *
     * @return wartosc kryptowaluty
     */

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
