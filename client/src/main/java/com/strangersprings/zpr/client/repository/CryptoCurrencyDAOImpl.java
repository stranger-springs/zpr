package com.strangersprings.zpr.client.repository;

import com.strangersprings.zpr.client.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.EnumMap;

import static com.strangersprings.zpr.client.model.CryptocurrencyType.*;

@Repository
public class CryptoCurrencyDAOImpl implements CryptoCurrencyDAO {

    private final JdbcTemplate jdbcTemplate;
    private final EnumMap<CryptocurrencyType, String> insertSQLs = new EnumMap<>(CryptocurrencyType.class);

    @Autowired
    public CryptoCurrencyDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

        insertSQLs.put(BTC, "INSERT INTO zpr.bitcoin(timestamp, price) VALUE (?,?);");
        insertSQLs.put(ETH, "INSERT INTO zpr.ethernum(timestamp, price) VALUE (?,?);");
        insertSQLs.put(LTC, "INSERT INTO zpr.litecoin(timestamp, price) VALUE (?,?);");
        insertSQLs.put(ZEC, "INSERT INTO zpr.zcash(timestamp, price) VALUE (?,?);");
    }

    @Override
    public void saveCurrencies(Bitcoin bitcoin, Ethernum ethernum, Litecoin litecoin, ZCash zCash) {
        jdbcTemplate.update(insertSQLs.get(BTC), bitcoin.getTimestamp(), bitcoin.getPrice());
        jdbcTemplate.update(insertSQLs.get(ETH), ethernum.getTimestamp(), ethernum.getPrice());
        jdbcTemplate.update(insertSQLs.get(LTC), litecoin.getTimestamp(), litecoin.getPrice());
        jdbcTemplate.update(insertSQLs.get(ZEC), zCash.getTimestamp(), zCash.getPrice());
    }
}
