package com.strangersprings.zpr.client.api.index;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UIIndexController.class)
class UIIndexControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UIIndexService service;

    @Test
    void findAll() throws Exception {
        String currencyType = "bitcoin";
        String indexType = "sma5";

        List<UIIndexDTO> dtos = Arrays.asList(
                createDTOWithIdAndPrice(1, 100.0, currencyType, indexType),
                createDTOWithIdAndPrice(2, 200.0, currencyType, indexType)
        );

        given(service.findAll(currencyType, indexType)).willReturn(dtos);

        mvc.perform(get("/index/" + currencyType + "/" + indexType)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].value", is(100.0)))
                .andExpect(jsonPath("$[0].currencyType", is(currencyType)))
                .andExpect(jsonPath("$[0].indexType", is(indexType)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].value", is(200.0)))
                .andExpect(jsonPath("$[1].currencyType", is(currencyType)))
                .andExpect(jsonPath("$[1].indexType", is(indexType)));
    }

    @Test
    void getLastOne() throws Exception {
        String currencyType = "bitcoin";
        String indexType = "sma5";
        given(service.getLastOne(currencyType, indexType)).willReturn(createDTOWithIdAndPrice(1, 100.0, currencyType, indexType));

        mvc.perform(get("/index/" + currencyType + "/" + indexType + "/last")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.value", is(100.0)))
                .andExpect(jsonPath("$.currencyType", is(currencyType)))
                .andExpect(jsonPath("$.indexType", is(indexType)));
    }

    private UIIndexDTO createDTOWithIdAndPrice(long id, double value, String currencyType, String indexType) {
        return UIIndexDTO.builder()
                .id(id)
                .currencyType(currencyType)
                .indexType(indexType)
                .timestamp(LocalDateTime.now())
                .value(value)
                .build();

    }
}