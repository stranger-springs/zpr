#include "com_strangersprings_zpr_client_service_calc_Calculator.h"
#include <iostream>

JNIEXPORT void JNICALL Java_com_strangersprings_zpr_client_service_calc_Calculator_sayHello
  (JNIEnv *, jobject) {
    std::string hello = "Hello from C++ !!";
      std::cout << hello << std::endl;
  }
