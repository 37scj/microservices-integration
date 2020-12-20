package br.com.fiap.drone.producer.service;

import br.com.fiap.drone.producer.models.DroneDTO;

/**
 * Test Service
 */
public interface AmqpService {

    void sendToConsumer(DroneDTO droneDTO);

}
