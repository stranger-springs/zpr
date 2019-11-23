package com.strangersprings.zpr.dataseriesserver.ethernum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class EthernumRepoProxy {

    private final EthernumRepository repository;

    @Autowired
    EthernumRepoProxy(EthernumRepository ethernumRepository) {
        this.repository = ethernumRepository;
    }

    List<Ethernum> findAll() {
        return repository.findAll();
    }

    Ethernum findLast() {
        return repository.findTopByOrderByTimestampDesc();
    }
}
