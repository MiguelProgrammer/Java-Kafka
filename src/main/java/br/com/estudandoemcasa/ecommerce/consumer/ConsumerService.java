package br.com.estudandoemcasa.ecommerce.consumer;

import br.com.estudandoemcasa.ecommerce.service.impl.KafkaServiceImpl;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsumerService {

    public static void main(String[] args)  {
        SpringApplication.run(ConsumerService.class, args);

        ConsumerService econsumer = new ConsumerService();
        KafkaServiceImpl kafkaService = new KafkaServiceImpl(ConsumerService.class.getName(), "ECOMMERCE_NEW_ORDER",econsumer::parse);
        kafkaService.run();
    }

    private void parse(ConsumerRecord<String, String> record) {
        System.out.println("--------------------------------");
        System.out.println(record.key());
        System.out.println(record.value());
        System.out.println(record.partition());
        System.out.println(record.offset());
        System.out.println("--------------------------------");
    }

}
