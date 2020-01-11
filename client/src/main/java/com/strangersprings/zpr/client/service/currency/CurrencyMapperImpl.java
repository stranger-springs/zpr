package com.strangersprings.zpr.client.service.currency;

import com.strangersprings.zpr.client.dto.currency.CurrencyDTO;
import com.strangersprings.zpr.client.model.Currency;
import com.strangersprings.zpr.client.model.CurrencyType;
import com.strangersprings.zpr.client.service.Utils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class CurrencyMapperImpl implements CurrencyMapper {

    @Override
    public Currency toCurrency(CurrencyType type, LocalDateTime timestamp, double price) {
        Currency currency = new Currency();
        currency.setType(type);
        currency.setTimestamp(timestamp);
        currency.setPrice(Utils.getRoundedPrice(price));
        return currency;
    }

    @Override
    public List<CurrencyDTO> toCurrencyDTOs(List<Currency> currencies) {
        List<CurrencyDTO> currencyDTOList = new ArrayList<>(currencies.size());
        for (Currency c : currencies) {
            currencyDTOList.add(toCurrencyDTO(c));
        }
        return currencyDTOList;
    }

    @Override
    public CurrencyDTO toCurrencyDTO(Currency currency) {
        return new CurrencyDTO(currency.getId(), currency.getPrice().doubleValue(), currency.getType().getName());
    }
}
