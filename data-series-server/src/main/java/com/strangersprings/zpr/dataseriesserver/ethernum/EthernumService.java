package com.strangersprings.zpr.dataseriesserver.ethernum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EthernumService {

    private final EthernumMapper mapper;
    private final EthernumRepoProxy repoProxy;

    @Autowired
    public EthernumService(EthernumMapper mapper, EthernumRepoProxy repoProxy) {
        this.mapper = mapper;
        this.repoProxy = repoProxy;
    }

    public List<EthernumDTO> findAll() {
        return repoProxy.findAll().stream()
                .map(mapper::toEthernumDTO)
                .collect(Collectors.toList());
    }

    public EthernumDTO findLastOne() {
        return mapper.toEthernumDTO(repoProxy.findLast());
    }
}
