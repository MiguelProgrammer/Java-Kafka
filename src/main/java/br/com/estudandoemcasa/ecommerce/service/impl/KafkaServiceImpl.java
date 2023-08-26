package br.com.estudandoemcasa.ecommerce.service.impl;

import br.com.estudandoemcasa.ecommerce.service.KafkaServiceConsumer;
import br.com.estudandoemcasa.ecommerce.util.GsonDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.util.ObjectUtils;

import java.time.Duration;
import java.util.*;
import java.util.regex.Pattern;

public class KafkaServiceImpl<T> {

    private final KafkaConsumer<String, T> consumer;
    private final KafkaServiceConsumer parse;

    public KafkaServiceImpl(String groupId, String topico, KafkaServiceConsumer parse, Class<T> type, Map<String, String> prop) {
        this.parse = parse;
        this.consumer = new KafkaConsumer<>(properties(type, groupId, prop));
        consumer.subscribe(Collections.singletonList(topico));
    }
    public KafkaServiceImpl(String groupId, Pattern topico, KafkaServiceConsumer parse, Class<T> type, Map<String, String> prop) {
        this.parse = parse;
        this.consumer = new KafkaConsumer<>(properties(type, groupId, prop));
        consumer.subscribe(topico);
    }

    public void run() {
        while (true) {
            var records = consumer.poll(Duration.ofMillis(100));
            if (!records.isEmpty()) {
                System.out.println("Enviando Mensagens");
                for (var record : records) {
                    parse.consume(record);
                }
            }
        }
    }


    private Properties properties (Class<T> type, String groupId, Map<String, String> overrideProp) {
        var properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, GsonDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.CLIENT_ID_CONFIG, groupId.concat(" - " + UUID.randomUUID().toString()));
        properties.setProperty(GsonDeserializer.TYPE_CONFIG, type.getName());
        properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "1");
        properties.putAll(overrideProp);
        return properties;
    }
}

