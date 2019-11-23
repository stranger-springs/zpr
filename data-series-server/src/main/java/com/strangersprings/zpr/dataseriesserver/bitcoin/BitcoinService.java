package com.strangersprings.zpr.dataseriesserver.bitcoin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BitcoinService {

    private final BitcoinMapper mapper;
    private final BitcoinRepoProxy repoProxy;

    @Autowired
    public BitcoinService(BitcoinMapper mapper, BitcoinRepoProxy repoProxy) {
        this.mapper = mapper;
        this.repoProxy = repoProxy;
    }

    public List<BitcoinDTO> findAll() {
        return repoProxy.findAll().stream()
                .map(mapper::toBitcoinDTO)
                .collect(Collectors.toList());
    }

    public BitcoinDTO findLastOne() {
        return mapper.toBitcoinDTO(repoProxy.findLast());
    }
}
