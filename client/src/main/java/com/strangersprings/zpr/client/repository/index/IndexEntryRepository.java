package com.strangersprings.zpr.client.repository.index;

import com.strangersprings.zpr.client.model.IndexEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndexEntryRepository extends JpaRepository<IndexEntry, Long> {

}
