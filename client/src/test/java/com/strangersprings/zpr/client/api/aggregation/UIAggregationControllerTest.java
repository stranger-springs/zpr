package com.strangersprings.zpr.client.api.aggregation;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UIAggregationController.class)
class UIAggregationControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UIAggregationService service;

    @Test
    void getHistoricalData() throws Exception {
        String currencyType = "bitcoin";
        String aggregationType = "minute";

        LocalDateTime startTime = LocalDateTime.ofEpochSecond(10000, 0, ZoneOffset.UTC);
        LocalDateTime endTime = LocalDateTime.ofEpochSecond(10000, 0, ZoneOffset.UTC);

        List<UIAggregationDTO> dtos = Arrays.asList(
                createDTOWithIdAndPrice(1, currencyType, aggregationType, 0.0, 2.0, 1.0),
                createDTOWithIdAndPrice(2, currencyType, aggregationType, 3.0, 5.0, 4.0)
        );

        given(service.getHistoricalData(currencyType, aggregationType, startTime, endTime)).willReturn(dtos);


        String endpoint = "/historical/" + aggregationType + "/" + currencyType + "?start="
                + startTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")) + "&end=" +
                endTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));

        mvc.perform(get(endpoint)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].min", is(0.0)))
                .andExpect(jsonPath("$[0].value", is(1.0)))
                .andExpect(jsonPath("$[0].max", is(2.0)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].min", is(3.0)))
                .andExpect(jsonPath("$[1].value", is(4.0)))
                .andExpect(jsonPath("$[1].max", is(5.0)));
    }


    private UIAggregationDTO createDTOWithIdAndPrice(long id, String type, String aggregationType, double min, double max, double value) {
        return UIAggregationDTO.builder()
                .id(id)
                .aggregationType(aggregationType)
                .currencyType(type)
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now())
                .max(max)
                .min(min)
                .value(value)
                .build();

    }
}