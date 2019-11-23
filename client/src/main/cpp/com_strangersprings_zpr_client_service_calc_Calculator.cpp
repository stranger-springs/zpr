#include "com_strangersprings_zpr_client_service_calc_Calculator.h"
#include <iostream>
#include <vector>
#include <numeric>
#include <algorithm>
#include "DataHolder.h"

class Bitcoin {
public:
    Bitcoin() : id_(-1), price_(-1) {}
    Bitcoin(int id, double price) : id_(id), price_(price) {}

    long id_;
    double price_;
};

class Singleton {
public:
    static Singleton& getInstance(); //nie można usunąć
    DataHolder<Bitcoin> getBuffer() const {return buffer_;}
    void insertBuffer(const Bitcoin& bitcoin) {buffer_.insert(bitcoin);}
private:
    Singleton() : buffer_(10) {}
    Singleton(const Singleton&) = delete;
    Singleton& operator=(const Singleton&) = delete;
    DataHolder<Bitcoin> buffer_;
};

Singleton& Singleton::getInstance() {
    static Singleton instance; //lokalna zmienna statyczna
    return instance;
}


JNIEXPORT void JNICALL Java_com_strangersprings_zpr_client_service_calc_Calculator_sayHello
  (JNIEnv *, jobject) {
    std::string hello = "Hello from C++ !!";
    std::cout << hello << std::endl;
}

JNIEXPORT jobject JNICALL Java_com_strangersprings_zpr_client_service_calc_Calculator_calculate
  (JNIEnv *env, jobject callingObject, jobject currencyDTO) {
    jclass currencyClass = env->GetObjectClass(currencyDTO);
    jclass indicatorClass = env->FindClass("com/strangersprings/zpr/client/service/calc/CurrencyIndicator");

    jmethodID midgetPrice = env->GetMethodID(currencyClass, "getPrice","()D");
    double price = env->CallDoubleMethod(currencyDTO, midgetPrice);
    std::cout << "Current price: " << price << std::endl;

    price += 2;

    std::cout << "Next price: " << price << std::endl;
    jobject newIndicator = env->AllocObject(indicatorClass);
    jfieldID valueField = env->GetFieldID(indicatorClass , "value", "D");

    env->SetDoubleField(newIndicator, valueField, price);

    return newIndicator;
}

JNIEXPORT void JNICALL Java_com_strangersprings_zpr_client_service_calc_Calculator_insertBitcoin
  (JNIEnv *env, jobject callingObject, jobject currencyDTO) {
    jclass currencyClass = env->GetObjectClass(currencyDTO);
    jfieldID jId = env->GetFieldID(currencyClass, "id","J");
    jfieldID jPrice = env->GetFieldID(currencyClass, "price","D");
    long id = env->GetLongField(currencyDTO, jId);
    double price = env->GetDoubleField(currencyDTO, jPrice);
    Singleton &s = Singleton::getInstance();
    Bitcoin bitcoin = Bitcoin(id, price);
    s.insertBuffer(bitcoin);
  }

JNIEXPORT jdouble JNICALL Java_com_strangersprings_zpr_client_service_calc_Calculator_bitcoinAverage
  (JNIEnv *env, jobject callingObject) {

    Singleton &s = Singleton::getInstance();
    DataHolder<Bitcoin> buffer = s.getBuffer();
    auto mapToPrice = [](Bitcoin b) { return b.price_;};
    std::vector<Bitcoin> bitcoins = buffer.getData();
    std::vector<double> prices;
    prices.resize(bitcoins.size());
    std::transform(bitcoins.begin(), bitcoins.end(), prices.begin(), mapToPrice);
    double average = accumulate(prices.begin(), prices.end(), 0.0)/prices.size();
    return average;
  }

JNIEXPORT void JNICALL Java_com_strangersprings_zpr_client_service_calc_Calculator_init
  (JNIEnv *env, jobject callingObject) {
     Singleton &s = Singleton::getInstance();
  }