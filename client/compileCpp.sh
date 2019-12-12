#!/bin/bash
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64

javaPath="./src/main/java/com/strangersprings/zpr/client/service/calc/"
cppPath="./src/main/cpp"
nativePath="../../../native/linux_x86_64/"

# update headers
javac -h ${cppPath} "${javaPath}Calculator.java" "${javaPath}CurrencyDTO.java" "${javaPath}CurrencyIndicesDTO.java"

cd ${cppPath}

# compile
g++ -c -fPIC -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux -Ilib \
  com_strangersprings_zpr_client_service_calc_Calculator.cpp -o com_strangersprings_zpr_client_service_calc_Calculator.o

# build shard library
g++ -shared -fPIC -o "${nativePath}libnative.so" com_strangersprings_zpr_client_service_calc_Calculator.o -lc

rm com_strangersprings_zpr_client_service_calc_Calculator.o

cd - || exit
