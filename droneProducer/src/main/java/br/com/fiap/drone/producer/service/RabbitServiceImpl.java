package br.com.fiap.drone.producer.service;

import br.com.fiap.drone.producer.models.DroneDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitServiceImpl implements AmqpService {

    @Autowired
    private AmqpProducer<DroneDTO> amqp;

    @Override
    public void sendToConsumer(DroneDTO droneDTO) {
        amqp.producer(droneDTO);
    }

}
