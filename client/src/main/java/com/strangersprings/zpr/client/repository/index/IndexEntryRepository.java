package com.strangersprings.zpr.client.repository.index;

import com.strangersprings.zpr.client.model.IndexEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndexEntryRepository extends JpaRepository<IndexEntry, Long> {

    @Query(nativeQuery = true, value =
            "SELECT index_entry.* FROM index_entry " +
                    "JOIN index_entry_type ON index_entry.index_entry_type_id = index_entry_type.id " +
                    "JOIN currency ON index_entry.currency_id = currency.id " +
                    "JOIN currency_type ON currency.currency_type_id = currency_type.id " +
                    "WHERE currency_type.name = ?1 AND index_entry_type.name = ?2 ORDER BY currency.id DESC LIMIT ?3")
    List<IndexEntry> findIndicesByTypeWithLimit(String currencyType, String indexType, int limit);

    IndexEntry findTopByCurrency_Type_NameAndType_NameOrderByIdDesc(String currencyType, String indexType);
}
