package com.strangersprings.zpr.client.repository;

import com.strangersprings.zpr.client.dto.index.IndexesDTO;

public interface IndexDAO {
    void saveIndex(IndexesDTO indexesDTO);
}
