package com.strangersprings.zpr.client.repository.currency;

import com.strangersprings.zpr.client.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

}
