package com.strangersprings.zpr.client.api.aggregation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/historical")
public class UIAggregationController {

    private final UIAggregationService service;

    @Autowired
    public UIAggregationController(UIAggregationService service) {
        this.service = service;
    }

    @GetMapping("/{aggregationType}/{currencyType}")
    public List<UIAggregationDTO> getHistoricalData(@PathVariable String currencyType, @PathVariable String aggregationType,
                                                    @RequestParam(name = "start") @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss") LocalDateTime startDate,
                                                    @RequestParam(name = "end") @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss") LocalDateTime endDate) {
        return service.getHistoricalData(currencyType, aggregationType, startDate, endDate);
    }
}
