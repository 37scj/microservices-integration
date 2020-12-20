package br.com.fiap.drone.producer.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import br.com.fiap.drone.producer.models.DroneDTO;
import br.com.fiap.drone.producer.service.AmqpProducer;

@Component
public class ProducerRabbitImpl implements AmqpProducer<DroneDTO> {

	private Logger logger = LoggerFactory.getLogger(ProducerRabbitImpl.class);

	@Value("${spring.rabbitmq.exchange}")
	private String exchange;

	@Value("${spring.rabbitmq.routing-key}")
	private String queue;

	@Value("${drone.url.updateInformation}")
	private String url;

	@RabbitListener(queues = "${spring.rabbitmq.routing-key}")
	@Override
	public void producer(DroneDTO droneDTO) {
		String json = null;
		try {
			json = new ObjectMapper().writeValueAsString(droneDTO);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		logger.info("Produzindo: " + json);

		try {
			atualizarDadosParaDatabase(droneDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param droneDTO
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void atualizarDadosParaDatabase(DroneDTO droneDTO) throws Exception {
		
	    RestTemplate template = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

		HttpHeaders headers = headers();
		HttpEntity<DroneDTO> request;
		request = new HttpEntity(droneDTO, headers);
		
		if(droneDTO.getId() != null) {
			try {
				template.exchange(url+String.valueOf(droneDTO.getId()),HttpMethod.PATCH, request, String.class);
			}catch(HttpClientErrorException e) {
				logger.error("DRONE NAO ENCONTRADO : " + e.getMessage());
			}catch(Exception e) {
				logger.error("ERRO AO REALIZAR A REQUISICAO : " + e.getMessage());
				e.printStackTrace();
			}
		}else {
			throw new Exception("DRONE SEM ID");
		}
	}

	private HttpHeaders headers() {
		HttpHeaders headers = new HttpHeaders();  
		headers.setContentType(MediaType.APPLICATION_JSON); 
		HttpEntity<DroneDTO> request;
		return headers;
	}

}
