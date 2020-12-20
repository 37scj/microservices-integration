package br.com.fiap.drone.producer.controllers;

import br.com.fiap.drone.producer.models.DroneDTO;
import br.com.fiap.drone.producer.service.AmqpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {


    @Autowired
    private AmqpService service;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/send")
    public void sendToConsumer(@RequestBody DroneDTO droneDTO) {
        service.sendToConsumer(droneDTO);
    }

}
