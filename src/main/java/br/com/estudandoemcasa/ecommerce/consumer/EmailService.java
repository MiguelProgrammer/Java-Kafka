package br.com.estudandoemcasa.ecommerce.consumer;

import br.com.estudandoemcasa.ecommerce.service.impl.KafkaServiceImpl;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class EmailService {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		SpringApplication.run(EmailService.class, args);

		EmailService emailConsumer = new EmailService();

		KafkaServiceImpl kafkaService = new KafkaServiceImpl(EmailService.class.getName(),"ECOMMERCE_SEND_EMAIL",emailConsumer::parse);
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
