package com.strangersprings.zpr.dataseriesserver;

import com.strangersprings.zpr.dataseriesserver.bitcoin.BitcoinDTO;
import com.strangersprings.zpr.dataseriesserver.bitcoin.BitcoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bitcoin")
public class BitcoinController {

    private final BitcoinService service;

    @Autowired
    public BitcoinController(BitcoinService service) {
        this.service = service;
    }

    @GetMapping
    public List<BitcoinDTO> findAll() {
        return service.findAll();
    }
}
