package com.strangersprings.zpr.client.repository.aggregation;

import com.strangersprings.zpr.client.model.Aggregation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AggregationRepository extends JpaRepository<Aggregation, Long> {

}
