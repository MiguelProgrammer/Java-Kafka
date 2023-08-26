package br.com.estudandoemcasa.ecommerce.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface KafkaServiceConsumer {

    void consume (ConsumerRecord<String, String> record);

}
