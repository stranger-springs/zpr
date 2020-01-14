package com.strangersprings.zpr.client.process.service.aggregation;

import com.strangersprings.zpr.client.model.Aggregation;
import com.strangersprings.zpr.client.process.dto.aggregation.AggregationDTO;
import com.strangersprings.zpr.client.process.dto.aggregation.CurrencyAggregationDTO;
import com.strangersprings.zpr.client.process.service.currency.CurrencyService;
import com.strangersprings.zpr.client.repository.aggregation.AggregationRepoProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Service
public class AggregationService {

    private final AggregationRepoProxy repository;
    private final AggregationMapper mapper;
    private final CurrencyService currencyService;
    private final AggregationTypeService aggregationTypeService;
    private final AggregationEntryTypeService aggregationEntryTypeService;

    @Autowired
    public AggregationService(AggregationRepoProxy repository, AggregationMapper mapper, CurrencyService service,
                              AggregationTypeService aggregationTypeService, AggregationEntryTypeService aggregationEntryTypeService) {
        this.repository = repository;
        this.mapper = mapper;
        this.currencyService = service;
        this.aggregationTypeService = aggregationTypeService;
        this.aggregationEntryTypeService = aggregationEntryTypeService;
    }

    public void saveAggregation(AggregationDTO aggregationDTO) {
        if (aggregationDTO.getResult().size() != 0) {
            List<Aggregation> aggregations = mapToAggregations(aggregationDTO);
            repository.saveAll(aggregations);
        }
    }

    private List<Aggregation> mapToAggregations(AggregationDTO aggregationDTO) {
        List<CurrencyAggregationDTO> currencyAggregationDTOS = aggregationDTO.getResult();
        List<Long> ids = currencyAggregationDTOS.stream()
                .flatMap(dtos -> Stream.of(dtos.getValue().getFirstId(), dtos.getValue().getLastId()))
                .collect(toList());
        return mapper.toAggregations(currencyAggregationDTOS, readData(ids));
    }

    private DataHolder readData(List<Long> ids) {
        return new DataHolder(currencyService.findAllByIds(ids), aggregationTypeService.getTypes(), aggregationEntryTypeService.getTypes());
    }
}
