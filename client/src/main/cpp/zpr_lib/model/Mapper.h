#ifndef ZPR_MAPPER_H
#define ZPR_MAPPER_H


#include <config/AggregationDTO.h>
#include "model/Currency.h"
#include "model/RealtimeIndex.h"
#include "model/Stat.h"
#include "config/CurrencyDTO.h"
#include "config/StatDTO.h"
#include "config/IndexesDTO.h"
#include "config/CurrenciesDTO.h"


class Mapper {
public:
    static std::map<std::string, std::shared_ptr<Currency>> toCurrenciesMap(const cf::CurrenciesDTO &dto);

    static cf::IndexesDTO toIndexesDTO(const std::map<std::string, std::vector<RealtimeIndex>> &indexes);

    static cf::AggregationDTO toAggregationDTO(const std::map<std::string, std::map<std::string, Stat>> &map);

    static std::shared_ptr<Currency> createCurrency(long id, double value);

    static std::string toJsonString(cf::IndexesDTO dto);

    static std::string toJsonString(cf::AggregationDTO dto);
private:

    static cf::StatDTO toStatDTO(const std::string& id, const Stat &stat);

    static std::vector<cf::Entry<cf::StatDTO>> toStatDTOEntries(const std::string& id, const std::map<std::string, Stat> &map);

    static std::vector<cf::Entry<double>> mapIndexesToEntries(const std::vector<RealtimeIndex> &vector);

    static std::vector<cf::Entry<double>> mapStatToEntries(const Stat &stat);
};


#endif //ZPR_MAPPER_H
