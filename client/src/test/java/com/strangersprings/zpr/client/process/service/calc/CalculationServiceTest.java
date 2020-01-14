package com.strangersprings.zpr.client.process.service.calc;

import com.strangersprings.zpr.client.process.Utils;
import com.strangersprings.zpr.client.process.dto.aggregation.AggregationDTO;
import com.strangersprings.zpr.client.process.dto.aggregation.CurrencyAggregationDTO;
import com.strangersprings.zpr.client.process.dto.aggregation.StatDTO;
import com.strangersprings.zpr.client.process.dto.aggregation.StatDTOEntry;
import com.strangersprings.zpr.client.process.dto.currency.CurrenciesDTO;
import com.strangersprings.zpr.client.process.dto.currency.CurrencyDTO;
import com.strangersprings.zpr.client.process.dto.index.IndexesDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class CalculationServiceTest {

    @Test
    public void testIndexAndAggregationCalculation() {
        CalculationService service = new CalculationService(Utils.loadConfigFile(this.getClass(), "TestConfig.json"));
        String currencyName = "bitcoin";
        int numOfElements = 5;
        List<CurrencyDTO> inputCurrencyDTOs = generateDTOs(Arrays.asList(10.0, 20.0, 30.0, 40.0, 50.0), currencyName);
        StatDTO expectedStatDTO = createStatDTO("minute", 1, 5, 10.0, 50.0, 30.0);

        for (CurrencyDTO dto : inputCurrencyDTOs.subList(0, numOfElements - 1)) {
            IndexesDTO indexesDTO = service.updateIndex(new CurrenciesDTO(singletonList(dto)));
            assertEquals("Indexes size", 1, indexesDTO.getIndexes().size());
            assertEquals("Indexes size", 2, indexesDTO.getIndexes().get(0).getEntries().size());
            AggregationDTO aggregationDTO = service.updateAggregation();
            assertEquals("No aggregations", 0, aggregationDTO.getResult().size());
        }

        IndexesDTO indexesDTO = service.updateIndex(new CurrenciesDTO(singletonList(inputCurrencyDTOs.get(numOfElements - 1))));
        assertEquals("Indexes size", 1, indexesDTO.getIndexes().size());
        assertEquals("Indexes entries size", 2, indexesDTO.getIndexes().get(0).getEntries().size());

        List<CurrencyAggregationDTO> aggregationDTOs = service.updateAggregation().getResult();
        assertEquals("Aggregation size should be 1", 1, aggregationDTOs.size());
        assertEquals("Aggregation currency name", currencyName, aggregationDTOs.get(0).getKey());
        assertEquals("Aggregation stat DTO", expectedStatDTO, aggregationDTOs.get(0).getValue());

    }

    private List<CurrencyDTO> generateDTOs(List<Double> values, String name) {
        long id = 0;
        List<CurrencyDTO> dtos = new ArrayList<>(values.size());
        for (Double v : values) {
            dtos.add(createDTO(++id, v, name));
        }
        return dtos;
    }

    private CurrencyDTO createDTO(long id, double value, String name) {
        return new CurrencyDTO(id, value, name);
    }

    private StatDTO createStatDTO(String id, long firstId, long lastId, double min, double max, double avg) {
        StatDTO dto = new StatDTO();
        dto.setId(id);
        dto.setFirstId(firstId);
        dto.setLastId(lastId);
        dto.setStats(Arrays.asList(
                createStatDTOEntry("avg", avg),
                createStatDTOEntry("min", min),
                createStatDTOEntry("max", max)
        ));
        return dto;
    }

    private StatDTOEntry createStatDTOEntry(String key, double value) {
        StatDTOEntry entry = new StatDTOEntry();
        entry.setKey(key);
        entry.setValue(value);
        return entry;
    }
}