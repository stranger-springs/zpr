package com.strangersprings.zpr.client.service.calc;

import com.strangersprings.zpr.client.dto.aggregation.AggregationDTO;
import com.strangersprings.zpr.client.dto.currency.CurrenciesDTO;
import com.strangersprings.zpr.client.dto.index.IndexesDTO;

public class CalculationService {

    private Calculator calculator;

    public CalculationService(String config) {
        calculator = new Calculator(config);
    }

    public IndexesDTO updateIndex(CurrenciesDTO currenciesDTO) {
        String input = JsonUtils.toJson(currenciesDTO);
        String result = calculator.calcIndex(input);
        return JsonUtils.fromJson(result, IndexesDTO.class);
    }

    public AggregationDTO updateAggregation() {
        String result = calculator.calcAggregation();
        return JsonUtils.fromJson(result, AggregationDTO.class);
    }
}
