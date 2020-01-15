package com.strangersprings.zpr.client.api.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/index")
public class UIIndexController {

    private final UIIndexService service;

    @Autowired
    public UIIndexController(UIIndexService service) {
        this.service = service;
    }

    /**
     * @param currencyType rodzaj kryptowaluty
     * @param indexType    rodzaj wskaznika
     * @return lista wartosci wskaznikow dla danej kryptowaluty
     */
    @GetMapping("/{currencyType}/{indexType}")
    public List<UIIndexDTO> findAll(@PathVariable String currencyType, @PathVariable String indexType) {
        return service.findAll(currencyType, indexType);
    }

    /**
     * @param currencyType rodzaj kryptowaluty
     * @param indexType    rodzaj wskaznika
     * @return ostatnia wartosc wskaznika zapisanego do bazy danych
     */

    @GetMapping("/{currencyType}/{indexType}/last")
    public UIIndexDTO getLastOne(@PathVariable String currencyType, @PathVariable String indexType) {
        return service.getLastOne(currencyType, indexType);
    }
}
