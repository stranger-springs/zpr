package com.strangersprings.zpr.client.repository.aggregation;

import com.strangersprings.zpr.client.model.AggregationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AggregationTypeRepository extends JpaRepository<AggregationType, Integer> {

}
