package br.com.fiap.drone.producer.controllers;

import br.com.fiap.drone.producer.models.DroneDTO;
import com.fasterxml.jackson.core.JsonFactory;
import com.rabbitmq.tools.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

import javax.sound.midi.Receiver;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RestController
public class ProducerController {

    private Logger logger = LoggerFactory.getLogger(ProducerController.class);

    private final RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.routing-key}")
    private String routingKey;
    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    public ProducerController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/send")
    public void sendToConsumer(@RequestBody DroneDTO droneDTO) {
        logger.info("Enviando info do drone para exchange: " + exchange + ", rota:" + routingKey + ", drone: "+ droneDTO.toString());
        rabbitTemplate.convertAndSend(exchange, routingKey, droneDTO);
    }

}
