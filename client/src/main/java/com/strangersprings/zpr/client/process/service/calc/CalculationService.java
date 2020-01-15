package com.strangersprings.zpr.client.process.service.calc;

import com.strangersprings.zpr.client.process.dto.aggregation.AggregationDTO;
import com.strangersprings.zpr.client.process.dto.currency.CurrenciesDTO;
import com.strangersprings.zpr.client.process.dto.index.IndexesDTO;

public class CalculationService {

    private Calculator calculator;

    public CalculationService(String config) {
        calculator = new Calculator(config);
    }

    /**
     * funkcja wykorzystujaca metody natywne modulu analitycznego C++ do obliczenia wartosci wskaznika
     * @param currenciesDTO obiekt przechowujacy kryptowalute
     * @return obiekt przechowujacy wyliczony wskaznik
     */
    public IndexesDTO updateIndex(CurrenciesDTO currenciesDTO) {
        String input = JsonUtils.toJson(currenciesDTO);
        String result = calculator.calcIndex(input);
        return JsonUtils.fromJson(result, IndexesDTO.class);
    }

    /**
     * funkcja wykorzystujaca metody natywne modulu analitycznego c++ do wyznaczenia wartosci zagregowanych
     * @return obiekt przechowujacy wyznaczona wartosc zagregowana
     */
    public AggregationDTO updateAggregation() {
        String result = calculator.calcAggregation();
        return JsonUtils.fromJson(result, AggregationDTO.class);
    }
}
