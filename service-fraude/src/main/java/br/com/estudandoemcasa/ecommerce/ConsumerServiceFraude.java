package br.com.estudandoemcasa.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

@SpringBootApplication
public class ConsumerServiceFraude {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerServiceFraude.class, args);

        ConsumerServiceFraude econsumer = new ConsumerServiceFraude();
        KafkaServiceImpl kafkaService = new KafkaServiceImpl<>(
                ConsumerServiceFraude.class.getName(),
                "ECOMMERCE_NEW_ORDER", econsumer::parse, Order.class,
                Map.of());
        kafkaService.run();
    }

    private void parse(ConsumerRecord<String, Order> record) {
        System.out.println("--------------------------------");
        System.out.println(record.key());
        System.out.println(record.value());
        System.out.println(record.partition());
        System.out.println(record.offset());
        System.out.println("--------------------------------");
    }

}
