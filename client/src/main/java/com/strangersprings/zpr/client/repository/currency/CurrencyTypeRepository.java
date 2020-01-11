package com.strangersprings.zpr.client.repository.currency;

import com.strangersprings.zpr.client.model.CurrencyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyTypeRepository extends JpaRepository<CurrencyType, Integer> {

}
