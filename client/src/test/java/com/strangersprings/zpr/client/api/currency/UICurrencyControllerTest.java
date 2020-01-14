package com.strangersprings.zpr.client.api.currency;

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
@WebMvcTest(UICurrencyController.class)
class UICurrencyControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UICurrencyService service;

    @Test
    void findAll() throws Exception {

        String type = "bitcoin";
        List<UICurrencyDTO> dtos = Arrays.asList(
                createDTOWithIdAndPrice(1, 100.0, type),
                createDTOWithIdAndPrice(2, 200.0, type)
        );

        given(service.findAll(type)).willReturn(dtos);

        mvc.perform(get("/currency/" + type)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].price", is(100.0)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].price", is(200.0)));

    }

    @Test
    void getLastOne() throws Exception {
        String type = "bitcoin";
        given(service.getLastOne(type)).willReturn(createDTOWithIdAndPrice(1, 100.0, type));

        mvc.perform(get("/currency/" + type + "/last")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.price", is(100.0)));
    }

    private UICurrencyDTO createDTOWithIdAndPrice(long id, double price, String type) {
        return UICurrencyDTO.builder()
                .id(id)
                .price(price)
                .timestamp(LocalDateTime.now())
                .type(type)
                .build();
    }
}