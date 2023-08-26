package br.com.estudandoemcasa.ecommerce.consumer;

import br.com.estudandoemcasa.ecommerce.service.impl.KafkaServiceImpl;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

@SpringBootApplication
public class LogService {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		SpringApplication.run(LogService.class, args);

		LogService logService = new LogService();
		KafkaServiceImpl kafkaService = new KafkaServiceImpl(
				LogService.class.getName(), Pattern.compile("ECOMMERCE.*"),
				logService::parse, String.class,
				Map.of(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName()));
		kafkaService.run();
	}

		private void parse(ConsumerRecord<String, String> record){
			System.out.println("------------LOG CONSUMER--------------");
			System.out.println(record.key());
			System.out.println(record.value());
			System.out.println(record.partition());
			System.out.println(record.offset());
			System.out.println("--------------------------------");
		}
	}

//	private static Properties properties() {
//		var properties = new Properties();
//		properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
//		properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
//		properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
//		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, LogService.class.getName());
//		properties.setProperty(ConsumerConfig.CLIENT_ID_CONFIG, LogService.class.getName().concat(" - "+ UUID.randomUUID().toString()));
//		properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "1");
//		return properties;
//	}

