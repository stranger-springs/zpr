package com.strangersprings.zpr.client.service;

import com.strangersprings.zpr.client.dto.index.IndexesDTO;
import com.strangersprings.zpr.client.repository.IndexDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexService {

    private final IndexDAO indexDAO;

    @Autowired
    public IndexService(IndexDAO indexDAO) {
        this.indexDAO = indexDAO;
    }

    public void saveIndexes(IndexesDTO indexesDTO) {
        indexDAO.saveIndex(indexesDTO);
    }
}
