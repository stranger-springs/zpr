#include <config/CurrenciesDTO.h>
#include <config/JsonUtils.h>
#include "TestUtils.h"
#include <streambuf>
#include <fstream>

std::vector<std::string>
TestUtils::generateInput(const std::vector<double> &values, const std::vector<std::string> &names) {

    std::vector<cf::CurrenciesDTO> dtos;
    dtos.reserve(values.size());

    long id = 0;
    for (double v : values) {
        cf::CurrenciesDTO currenciesDto;
        for (const auto &name : names) {
            currenciesDto.currencies.emplace_back(id, name, v);
        }
        id++;
        dtos.push_back(currenciesDto);
    }

    std::vector<std::string> result;
    result.reserve(dtos.size());

    std::transform(dtos.begin(), dtos.end(), result.begin(),
                   [](const cf::CurrenciesDTO &dto) { return cf::JsonUtils<cf::CurrenciesDTO>::toString(dto); });

    return result;
}

std::string TestUtils::loadStringFromFile(const std::string &fileName) {
    std::ifstream t(fileName);

    if (t.is_open()) {
        std::string str((std::istreambuf_iterator<char>(t)),
                        std::istreambuf_iterator<char>());
        return str;
    }

    return "";
}
