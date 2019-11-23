package com.strangersprings.zpr.dataseriesserver;

import com.strangersprings.zpr.dataseriesserver.ethernum.EthernumDTO;
import com.strangersprings.zpr.dataseriesserver.ethernum.EthernumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/ethernum")
public class EthernumController {

    private final EthernumService service;

    @Autowired
    public EthernumController(EthernumService service) {
        this.service = service;
    }

    @GetMapping
    public List<EthernumDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/last")
    public EthernumDTO getLastOne() {
        return service.findLastOne();
    }

}
