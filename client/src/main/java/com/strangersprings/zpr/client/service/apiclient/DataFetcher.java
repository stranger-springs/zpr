package com.strangersprings.zpr.client.service.apiclient;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class DataFetcher<DataT> implements FetchDataOperation<ResponseEntity<DataT>> {

    private final Class<DataT> typeParameterClass;
    private final String endpoint;
    private final HttpEntity entity;
    private final RestTemplate restTemplate = new RestTemplate();

    DataFetcher(String uri, HttpEntity entity, Class<DataT> typeParameterClass) {
        this.endpoint = uri;
        this.entity = entity;
        this.typeParameterClass = typeParameterClass;
    }

    @Override
    public ResponseEntity<DataT> getCurrentData() {
        return restTemplate.exchange(endpoint, HttpMethod.GET, entity, typeParameterClass);
    }
}
