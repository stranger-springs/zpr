#ifndef ZPR_TESTUTILS_H
#define ZPR_TESTUTILS_H


#include <vector>
#include <string>
#include <map>

class TestUtils {
public:
    static std::string loadStringFromFile(const std::string& fileName);

    static std::vector<std::string> generateInput(const std::vector<double> &values, const std::vector<std::string> &names);
};


#endif //ZPR_TESTUTILS_H
