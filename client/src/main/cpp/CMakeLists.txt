cmake_minimum_required(VERSION 3.10.2)
project(zpr)
set(CMAKE_CXX_STANDARD 14)
set(CMAKE_POSITION_INDEPENDENT_CODE ON)

find_package(nlohmann_json 3.7.3 REQUIRED)
find_package(JNI)

if (JNI_FOUND)
    message (STATUS "JNI_INCLUDE_DIRS=${JNI_INCLUDE_DIRS}")
    message (STATUS "JNI_LIBRARIES=${JNI_LIBRARIES}")
endif()

include_directories(zpr_lib)
add_subdirectory(zpr_lib)

include_directories(${JNI_INCLUDE_DIRS})
set(SOURCE_FILES
    com_strangersprings_zpr_client_process_service_calc_Calculator.cpp
)

add_library(native SHARED ${SOURCE_FILES} )

target_link_libraries(native
        PRIVATE nlohmann_json::nlohmann_json
        zpr_lib
)