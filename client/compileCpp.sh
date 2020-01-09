#!/bin/bash
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64

javaPath="./src/main/java/com/strangersprings/zpr/client/service/calc/"
cppPath="./src/main/cpp"
nativePath="../../../../native/linux_x86_64/"
filename="libnative.so"

# update headers
javac -h ${cppPath} "${javaPath}Calculator.java"

cd ${cppPath} || exit

mkdir -p .build
cd .build || exit
cmake ..
make

#copy
cp ${filename} "${nativePath}${filename}"

cd - || exit
