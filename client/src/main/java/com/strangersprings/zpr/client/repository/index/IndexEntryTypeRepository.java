package com.strangersprings.zpr.client.repository.index;

import com.strangersprings.zpr.client.model.IndexEntryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndexEntryTypeRepository extends JpaRepository<IndexEntryType, Integer> {

}
