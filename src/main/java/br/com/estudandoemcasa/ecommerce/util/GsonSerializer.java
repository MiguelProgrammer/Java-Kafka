package br.com.estudandoemcasa.ecommerce.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.kafka.common.serialization.Serializer;

public class GsonSerializer<T> implements Serializer<T> {

    private final Gson gson = new GsonBuilder().create();

    @Override
    public byte[] serialize(String s, T type) {
        return gson.toJson(type).getBytes();
    }

}
