package br.com.estudandoemcasa.ecommerce.producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class ProducerApplication {


	public static void main(String[] args) throws ExecutionException, InterruptedException {
		SpringApplication.run(ProducerApplication.class, args);

		var producer = new KafkaProducer<String, String>(properties());
		var value = "12345,7771,250.90";
		var email = "Thank you for your order! We are processing your order.";

		var record = new ProducerRecord<>("ECOMMERCE_NEW_ORDER", value, value);
		var emailRecord = new ProducerRecord<>("ECOMMERCE_SEND_EMAIL", email, email);
		Callback callback = (data, ex) -> {
			if (ex != null) {
				ex.getStackTrace();
				return;
			}
			System.out.println("SUCCESS: " + data.topic() +
					":::/ PARTIÇÃO " + data.partition() +
					":::/ OFFSET " + data.offset() +
					":::/ TIMESTAMP " + data.timestamp());
		};
		producer.send(record, callback).get();
		producer.send(emailRecord, callback).get();

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
