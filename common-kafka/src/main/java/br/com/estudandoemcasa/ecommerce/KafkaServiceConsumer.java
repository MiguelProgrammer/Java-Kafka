package br.com.estudandoemcasa.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface KafkaServiceConsumer<T> {

    void consume (ConsumerRecord<String, T> record);

}
