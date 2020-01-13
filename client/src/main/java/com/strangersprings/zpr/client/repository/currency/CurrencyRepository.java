package com.strangersprings.zpr.client.repository.currency;

import com.strangersprings.zpr.client.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    @Query(nativeQuery = true, value =
            "SELECT currency.* FROM currency " +
                    "JOIN currency_type ON currency.currency_type_id = currency_type.id " +
                    "WHERE currency_type.name = ?1 ORDER BY currency.id DESC LIMIT ?2")
    List<Currency> findCurrenciesByTypeWithLimit(String type, int limit);

    Currency findTopByType_NameOrderByIdDesc(String type);
}
