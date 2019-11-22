package com.strangersprings.zpr.dataseriesserver.bitcoin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class BitcoinRepoProxy {

    private final BitcoinRepository repository;

    @Autowired
    BitcoinRepoProxy(BitcoinRepository bitcoinRepository) {
        this.repository = bitcoinRepository;
    }

    List<Bitcoin> findAll() {
        return repository.findAll();
    }
}
