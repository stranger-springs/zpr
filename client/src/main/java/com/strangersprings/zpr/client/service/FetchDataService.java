package com.strangersprings.zpr.client.service;

import com.strangersprings.zpr.client.model.CryptocurrencyType;
import com.strangersprings.zpr.client.model.CurrencyData;
import com.strangersprings.zpr.client.repository.CryptoCurrencyDAO;
import com.strangersprings.zpr.client.service.calc.Calculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.EnumMap;
import java.util.Objects;

import static com.strangersprings.zpr.client.model.CryptocurrencyType.*;
import static com.strangersprings.zpr.client.service.Utils.getCurrentTimestamp;

@Service
public class FetchDataService {

    private final DataFetcher<CurrencyData> bitcoinFetcher;
    private final DataFetcher<CurrencyData> ethernumFetcher;
    private final DataFetcher<CurrencyData> litecoinFetcher;
    private final DataFetcher<CurrencyData> zcashFetcher;

    private final CryptoCurrencyDAO cryptoCurrencyDAO;

    private final EnumMap<CryptocurrencyType, String> endpoints;
    private final CryptocurrencyMapper cryptoCurrencyMapper;
    private final Calculator calculator;

    @Autowired
    public FetchDataService(CryptoCurrencyDAO cryptoCurrencyDAO, CryptocurrencyMapper currencyMapper, @Value("${app.api.url}") String apiUrl) {
        this.cryptoCurrencyDAO = cryptoCurrencyDAO;
        this.cryptoCurrencyMapper = currencyMapper;
        this.calculator = new Calculator();

        System.out.println("api url is: " + apiUrl);

        endpoints = new EnumMap<>(CryptocurrencyType.class);
        endpoints.put(BTC, apiUrl + "/currency/bitcoin");
        endpoints.put(ETH, apiUrl + "/currency/ethernum");
        endpoints.put(LTC, apiUrl + "/currency/litecoin");
        endpoints.put(ZEC, apiUrl + "/currency/zcash");

        this.bitcoinFetcher = new DataFetcher<>(endpoints.get(BTC), HttpEntity.EMPTY, CurrencyData.class);
        this.ethernumFetcher = new DataFetcher<>(endpoints.get(ETH), HttpEntity.EMPTY, CurrencyData.class);
        this.litecoinFetcher = new DataFetcher<>(endpoints.get(LTC), HttpEntity.EMPTY, CurrencyData.class);
        this.zcashFetcher = new DataFetcher<>(endpoints.get(ZEC), HttpEntity.EMPTY, CurrencyData.class);
    }

    @Scheduled(fixedRate = 5000, initialDelay = 5000)
    private void update() {
        LocalDateTime before = LocalDateTime.now();
        ResponseEntity<CurrencyData> bitcoin = bitcoinFetcher.getCurrentData();
        ResponseEntity<CurrencyData> ethernum = ethernumFetcher.getCurrentData();
        ResponseEntity<CurrencyData> litecoin = litecoinFetcher.getCurrentData();
        ResponseEntity<CurrencyData> zcash = zcashFetcher.getCurrentData();

        LocalDateTime timestamp = getCurrentTimestamp();

        // insert to buffer in cpp
        calculator.insertBitcoinDTO(cryptoCurrencyMapper.toCurrencyDTO(Objects.requireNonNull(bitcoin.getBody())));

        cryptoCurrencyDAO.saveCurrencies(
                cryptoCurrencyMapper.toBitcoin(Objects.requireNonNull(bitcoin.getBody()), timestamp),
                cryptoCurrencyMapper.toEthernum(Objects.requireNonNull(ethernum.getBody()), timestamp),
                cryptoCurrencyMapper.toLiteCoin(Objects.requireNonNull(litecoin.getBody()), timestamp),
                cryptoCurrencyMapper.toZcash(Objects.requireNonNull(zcash.getBody()), timestamp)
        );

        System.out.println("Update time: " + ChronoUnit.MILLIS.between(before, LocalDateTime.now()));
    }

    // test for scheduling native method call
    @Scheduled(fixedRate = 30000, initialDelay = 30000)
    public void updateAverage() {
        System.out.println("Update Average :" + calculator.getBitcoinAverage());
    }


}
