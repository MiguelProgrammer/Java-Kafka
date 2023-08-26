package br.com.estudandoemcasa.ecommerce.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class GsonDeserializer<T> implements Deserializer<Object> {

    public static final String TYPE_CONFIG = "br.com.estudandoemcasa.ecommerce.type_config";
    private final Gson gson = new GsonBuilder().create();
    private Class<T> type;

    @Override
    public Object deserialize(String s, byte[] bytes) {
        return gson.fromJson(new String(bytes), type);
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        String typeName = String.valueOf(configs.get(TYPE_CONFIG));
        try {
            this.type = (Class<T>) Class.forName(typeName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
