package com.strangersprings.zpr.dataseriesserver.bitcoin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BitcoinRepository extends JpaRepository<Bitcoin, Long> {

    Bitcoin findTopByOrderByTimestampDesc();
}
