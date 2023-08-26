package br.com.estudandoemcasa.ecommerce.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public interface KafkaService {

    void consume (ConsumerRecord<String, String> record);

}
