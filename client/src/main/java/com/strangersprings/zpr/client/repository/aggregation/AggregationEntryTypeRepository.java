package com.strangersprings.zpr.client.repository.aggregation;

import com.strangersprings.zpr.client.model.AggregationEntryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AggregationEntryTypeRepository extends JpaRepository<AggregationEntryType, Integer> {

}
