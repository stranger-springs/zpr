package com.strangersprings.zpr.client.api.currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/currency")
public class UICurrencyController {

    private final UICurrencyService service;

    @Autowired
    public UICurrencyController(UICurrencyService service) {
        this.service = service;
    }

    @GetMapping("/{type}")
    public List<UICurrencyDTO> findAll(@PathVariable String type) {
        return service.findAll(type);
    }

    @GetMapping("/{type}/last")
    public UICurrencyDTO getLastOne(@PathVariable String type) {
        return service.getLastOne(type);
    }

}
