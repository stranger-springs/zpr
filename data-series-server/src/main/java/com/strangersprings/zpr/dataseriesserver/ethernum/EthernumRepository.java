package com.strangersprings.zpr.dataseriesserver.ethernum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EthernumRepository extends JpaRepository<Ethernum, Long> {

    Ethernum findTopByOrderByTimestampDesc();
}
