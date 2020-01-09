package com.strangersprings.zpr.client.service;

import com.strangersprings.zpr.client.dto.aggregation.AggregationDTO;
import com.strangersprings.zpr.client.repository.AggregationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AggregationService {

    private final AggregationDAO aggregationDAO;

    @Autowired
    public AggregationService(AggregationDAO aggregationDAO){
        this.aggregationDAO = aggregationDAO;
    }

    public void saveAggregation(AggregationDTO aggregationDTO) {
        if(!aggregationDTO.getResults().isEmpty()){
            aggregationDAO.saveAggregation(aggregationDTO);
        }
    }
}
