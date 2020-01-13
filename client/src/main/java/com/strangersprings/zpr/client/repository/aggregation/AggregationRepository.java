package com.strangersprings.zpr.client.repository.aggregation;

import com.strangersprings.zpr.client.model.Aggregation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AggregationRepository extends JpaRepository<Aggregation, Long> {

    @Query(nativeQuery = true, value =
            "SELECT aggregation.* FROM aggregation " +
                    "JOIN aggregation_type ON aggregation.aggregation_type_id = aggregation_type.id " +
                    "JOIN currency AS first ON aggregation.first_id = first.id " +
                    "JOIN currency AS last ON aggregation.last_id = last.id " +
                    "JOIN currency_type ON first.currency_type_id = currency_type.id " +
                    "WHERE first.timestamp BETWEEN :fromDate AND :toDate AND last.timestamp BETWEEN :fromDate AND :toDate " +
                    "AND currency_type.name = :currencyType AND aggregation_type.name = :aggregationType " +
                    "ORDER BY first.id DESC LIMIT :limit")
    List<Aggregation> findAggregationsWithLimit(@Param("currencyType") String currencyType, @Param("aggregationType") String aggregationType,
                                                @Param("fromDate") LocalDateTime startDate, @Param("toDate") LocalDateTime endDate, @Param("limit") int limit);
}
