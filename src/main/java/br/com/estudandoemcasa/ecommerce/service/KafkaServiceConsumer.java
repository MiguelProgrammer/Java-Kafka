package br.com.estudandoemcasa.ecommerce.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface KafkaServiceConsumer<T> {

    void consume (ConsumerRecord<String, T> record);

}
