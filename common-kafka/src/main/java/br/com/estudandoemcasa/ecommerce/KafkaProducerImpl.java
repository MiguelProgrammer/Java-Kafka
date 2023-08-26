package br.com.estudandoemcasa.ecommerce;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.Closeable;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaProducerImpl<T> implements Closeable {


    private final KafkaProducer<String, T> producer;

    public KafkaProducerImpl() {
        this.producer = new KafkaProducer<>(properties());
    }

    private static Properties properties() {
        var properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, GsonSerializer.class.getName());
        return properties;
    }

    public void send(String topíc, String key, T value) throws ExecutionException, InterruptedException {

        var record = new ProducerRecord<>(topíc, key, value);
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
    }

    @Override
    public void close() throws IOException {
        producer.flush();
        producer.close();
    }
}

