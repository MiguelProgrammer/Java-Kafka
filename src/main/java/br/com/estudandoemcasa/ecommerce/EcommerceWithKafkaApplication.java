package br.com.estudandoemcasa.ecommerce;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class EcommerceWithKafkaApplication {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		SpringApplication.run(EcommerceWithKafkaApplication.class, args);

		var producer = new KafkaProducer<String, String>(properties());
		var value = "12345,7771,250.90";
			var record = new ProducerRecord<>("ECOMMERCE_NEW_ORDER", value, value);
			producer.send(record, (data, ex) -> {
				if (ex != null) {
					ex.getStackTrace();
					return;
				}
				System.out.println("SUCCESS: " + data.topic() +
						":::/ PARTIÇÃO " + data.partition() +
						":::/ OFFSET " + data.offset() +
						":::/ TIMESTAMP " + data.timestamp());
			}).get();
			producer.flush();
			producer.close();
		}
	private static Properties properties() {
		var properties = new Properties();
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		return properties;
	}
}
