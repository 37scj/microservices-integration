package br.com.fiap.drone.producer.listeners;

import br.com.fiap.drone.producer.models.DroneDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ListenerRabbit {

    private Logger logger = LoggerFactory.getLogger(ListenerRabbit.class);

    @Value("${drone.url.update:http://localhost:8090/drones/%d}")
    private String url;

    @Autowired
    RestTemplate template;

    @RabbitListener(queues = "${spring.rabbitmq.routing-key}")
    public void receiveMessage(DroneDTO droneDTO) {
        logger.info("Recebendo mensagem");
        String json = "";
        try {
            json = new ObjectMapper().writeValueAsString(droneDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        logger.info("Recebido: " + json);

        try {
            atualizarDadosParaDatabase(droneDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @param droneDTO
     * @throws Exception
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private void atualizarDadosParaDatabase(DroneDTO droneDTO) throws Exception {

        HttpEntity<DroneDTO> request = new HttpEntity(droneDTO, headers());

        if (droneDTO.getId() != null) {
            try {
                ResponseEntity<String> response = template.exchange(String.format(url, droneDTO.getId()),
                        HttpMethod.PATCH, request, String.class);
                LoggerFactory.getLogger(this.getClass()).info("Resposta da atualização: " + response);
            } catch (HttpClientErrorException e) {
                logger.error("DRONE NAO ENCONTRADO : " + e.getMessage());
            } catch (Exception e) {
                logger.error("ERRO AO REALIZAR A REQUISICAO : " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            throw new Exception("DRONE SEM ID");
        }
    }

    private HttpHeaders headers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

}
