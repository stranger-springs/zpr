package com.strangersprings.zpr.client.service;


import com.google.gson.Gson;

public class JsonUtils {
    static <T> T fromJson(String jsonString, Class<T> classType) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, classType);
    }
    static <T> String toJson(T obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }
}
