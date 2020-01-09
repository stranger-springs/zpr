#include <config/JsonUtils.h>
#include "model/Mapper.h"
#include "config/CurrencyDTO.h"
#include "MapperUtils.h"

std::map<std::string, std::shared_ptr<Currency>> Mapper::toCurrenciesMap(const cf::CurrenciesDTO &dto) {
    std::map<std::string, std::shared_ptr<Currency>> result;
    for (const cf::CurrencyDTO &c : dto.currencies) {
        result.insert(std::make_pair(c.name, createCurrency(c.id, c.value)));
    }
    return result;
}

std::shared_ptr<Currency> Mapper::createCurrency(long id, double value) {
    return std::make_shared<Currency>(id, value);
}

cf::IndexesDTO Mapper::toIndexesDTO(const std::map<std::string, std::vector<RealtimeIndex>> &indexes) {
    cf::IndexesDTO indexesDto;
    for (const auto &pair : indexes) {
        indexesDto.indexes.emplace_back(pair.first, mapIndexesToEntries(pair.second));
    }
    return indexesDto;
}

std::vector<cf::Entry<double>> Mapper::mapIndexesToEntries(const std::vector<RealtimeIndex> &vector) {
    std::vector<cf::Entry<double>> result;
    result.reserve(vector.size());
    std::transform(vector.begin(), vector.end(), std::back_inserter(result), [](const RealtimeIndex &ri) {
        cf::Entry<double> entry;
        entry.key = ri.getName();
        entry.value = ri.getValue();
        return entry;
    });
    return result;
}

cf::AggregationDTO Mapper::toAggregationDTO(const std::map<std::string, std::map<std::string, Stat>> &statMap) {
    cf::AggregationDTO aggregationDto;
    std::vector<cf::Entry<cf::StatDTO>> entries;
    for (auto &pair : statMap) {
        std::vector<cf::Entry<cf::StatDTO>> statDTOs = toStatDTOEntries(pair.first, pair.second);
        entries.insert(entries.end(), std::begin(statDTOs), std::end(statDTOs));
    }
    return cf::AggregationDTO(entries);
}

std::vector<cf::Entry<cf::StatDTO>>
Mapper::toStatDTOEntries(const std::string &id, const std::map<std::string, Stat> &map) {
    std::vector<cf::Entry<cf::StatDTO>> result;
    result.reserve(map.size());
    for (auto &pair : map) {
        result.push_back(cf::make_entry(id, toStatDTO(pair.first, pair.second)));
    }
    return result;
}

cf::StatDTO Mapper::toStatDTO(const std::string &id, const Stat &stat) {
    return cf::StatDTO(id, mapStatToEntries(stat));
}

std::vector<cf::Entry<double>> Mapper::mapStatToEntries(const Stat &stat) {
    std::vector<cf::Entry<double>> result;
    result.reserve(3);
    result.push_back(cf::make_entry("avg", stat.getValue()));
    result.push_back(cf::make_entry("min", stat.getMin()));
    result.push_back(cf::make_entry("max", stat.getMax()));
    return result;
}

std::string Mapper::toJsonString(cf::IndexesDTO dto) {
    return cf::JsonUtils<cf::IndexesDTO>::toString(dto);
}

std::string Mapper::toJsonString(cf::AggregationDTO dto) {
    return cf::JsonUtils<cf::AggregationDTO>::toString(dto);
}
