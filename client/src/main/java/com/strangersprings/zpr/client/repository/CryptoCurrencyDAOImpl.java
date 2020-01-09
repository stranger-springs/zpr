package com.strangersprings.zpr.client.repository;

import com.strangersprings.zpr.client.config.AppConfig;
import com.strangersprings.zpr.client.config.CurrencyConfig;
import com.strangersprings.zpr.client.dto.currency.CurrenciesDTO;
import com.strangersprings.zpr.client.dto.currency.CurrencyDTO;
import com.strangersprings.zpr.client.service.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CryptoCurrencyDAOImpl implements CryptoCurrencyDAO {

    private final JdbcTemplate jdbcTemplate;
    private final Map<String, String> insertSQLs = new HashMap<>();

    @Autowired
    public CryptoCurrencyDAOImpl(JdbcTemplate jdbcTemplate, AppConfig appConfig) {
        this.jdbcTemplate = jdbcTemplate;
        for (CurrencyConfig config : appConfig.getCurrencies()) {
            insertSQLs.put(config.getName(), "INSERT INTO " + config.getTableName() + "(timestamp, price) VALUE (?,?);");
        }
    }

    @Override
    public void saveCurrencies(CurrenciesDTO currenciesDTO) {
        for (CurrencyDTO dto : currenciesDTO.getCurrencies()) {
            if (insertSQLs.containsKey(dto.getName())) {
                jdbcTemplate.update(insertSQLs.get(dto.getName()), Utils.timestampFromSeconds(dto.getId()), dto.getValue());
            }
        }
    }
}
