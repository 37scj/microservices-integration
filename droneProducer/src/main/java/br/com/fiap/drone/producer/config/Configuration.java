package br.com.fiap.drone.producer.config;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@org.springframework.context.annotation.Configuration
public class Configuration {
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder
				.requestFactory(HttpComponentsClientHttpRequestFactory.class)
				.setConnectTimeout(Duration.ofMillis(300000))
			    .setReadTimeout(Duration.ofMillis(300000)).build();
	}
}
