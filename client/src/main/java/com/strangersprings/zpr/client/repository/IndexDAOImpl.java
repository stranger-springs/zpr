package com.strangersprings.zpr.client.repository;

import com.strangersprings.zpr.client.dto.index.IndexesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class IndexDAOImpl implements IndexDAO{

    private final JdbcTemplate jdbcTemplate;
    private final Map<String, String> insertSQLs = new HashMap<>();

    @Autowired
    public IndexDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void saveIndex(IndexesDTO indexesDTO) {

    }
}
