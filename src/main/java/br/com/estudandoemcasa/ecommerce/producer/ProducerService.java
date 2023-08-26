package br.com.estudandoemcasa.ecommerce.producer;

import br.com.estudandoemcasa.ecommerce.model.Order;
import br.com.estudandoemcasa.ecommerce.service.impl.KafkaProducerImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.Closeable;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class ProducerService {


    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        SpringApplication.run(ProducerService.class, args);

        try (var producerOrder = new KafkaProducerImpl<Order>()) {
            try (var producerEmail = new KafkaProducerImpl<String>()) {

                for (int i = 0; i < 15; i++) {
                    var userId = UUID.randomUUID().toString();
                    var orderId = UUID.randomUUID().toString();
                    var value = new BigDecimal("1023");
                    var email = "Thank you for your order! We are processing your order.";
                    Order order = new Order(userId, orderId, value);

					producerOrder.send("ECOMMERCE_NEW_ORDER", userId, order);
					producerEmail.send("ECOMMERCE_SEND_EMAIL", userId, email);
                }
            }
        }
    }
}
